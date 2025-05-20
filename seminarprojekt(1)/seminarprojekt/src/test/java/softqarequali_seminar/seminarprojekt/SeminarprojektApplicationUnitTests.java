package softqarequali_seminar.seminarprojekt;

import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SeminarprojektApplicationUnitTests {

	@Autowired
	 HTMLController controller;

	@Autowired
	ProjectController projectController;

	@Autowired
	MqttConfig.MyGateway gateway;

	@Autowired
	SaveDataService saveDataService;

	@Autowired
	S7_1500_Differenz_Repository s7_1500_differenz_repository;

	@Autowired
	private MockMvc mockMvc;

	// teste ob die Application überhaupt starten kann und der nötige context geladen wird
	@Test
	void contextLoads() {
		assertThat(projectController).isNotNull();
		assertThat(gateway).isNotNull();
		assertThat(controller).isNotNull();

	}

	// basic GET response testen
	@Test
	void shouldReceiveGetResponse() throws Exception {
		assertThat(projectController.hello()).contains("Guten Tag");
	}

	@Test
	void dbShouldSaveDataInRepo(){
		S7_1500_Differenz data = new S7_1500_Differenz("payload", null, (long) 1);

		S7_1500_Differenz savedData = s7_1500_differenz_repository.save(data);
		assertThat(savedData.payload).isEqualTo("payload");
	}

	@Test
	void dbShouldFindLatestData(){
		// bisherigen neusten timestamp finden
		long newestTS = s7_1500_differenz_repository.findTopByOrderByTimestampDesc().timestamp;

		// was neueres Erzeugen
		S7_1500_Differenz data = new S7_1500_Differenz("payload", null, newestTS +100);
		S7_1500_Differenz savedData = s7_1500_differenz_repository.save(data);

		String newest_id = s7_1500_differenz_repository.findTopByOrderByTimestampDesc().id;

		assertThat(newest_id).isEqualTo(savedData.id);
	}

	@Test
	void shouldConvertWago750PayloadToBinaryArray(){
		Wago750 w = new Wago750("[1]", null, (long)1);

		int[] a = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

		assertThat(w.binaryArray).isNotNull();
		assertArrayEquals(w.binaryArray, a);
	}

	@Test
	void saveDataServiceShouldClassifyTopics(){

		// Mock Message because making the real thing would be very annoying
		Message m1 = Mockito.mock(Message.class);
		MessageHeaders mh = Mockito.mock(MessageHeaders.class);

		Message m2 = Mockito.mock(Message.class);
		MessageHeaders mh2 = Mockito.mock(MessageHeaders.class);

		Message m3 = Mockito.mock(Message.class);
		MessageHeaders mh3 = Mockito.mock(MessageHeaders.class);

		Message m4 = Mockito.mock(Message.class);
		MessageHeaders mh4 = Mockito.mock(MessageHeaders.class);

		Message m5 = Mockito.mock(Message.class);
		MessageHeaders mh5 = Mockito.mock(MessageHeaders.class);


		Mockito.when(m1.getHeaders()).thenReturn(mh);
		Mockito.when(m1.getPayload()).thenReturn("some payload");
		Mockito.when(mh.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh.get("mqtt_receivedTopic")).thenReturn("S7_1500/Temperatur/Ist");

		Mockito.when(m2.getHeaders()).thenReturn(mh2);
		Mockito.when(m2.getPayload()).thenReturn("some payload");
		Mockito.when(mh2.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh2.get("mqtt_receivedTopic")).thenReturn("S7_1500/Temperatur/Soll");

		Mockito.when(m3.getHeaders()).thenReturn(mh3);
		Mockito.when(m3.getPayload()).thenReturn("some payload");
		Mockito.when(mh3.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh3.get("mqtt_receivedTopic")).thenReturn("S7_1500/Temperatur/Differenz");

		Mockito.when(m4.getHeaders()).thenReturn(mh4);
		Mockito.when(m4.getPayload()).thenReturn("[1]"); // braucht zahl
		Mockito.when(mh4.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh4.get("mqtt_receivedTopic")).thenReturn("Wago750/Status");

		Mockito.when(m5.getHeaders()).thenReturn(mh5);
		Mockito.when(m5.getPayload()).thenReturn("1");
		Mockito.when(mh5.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh5.get("mqtt_receivedTopic")).thenReturn("ungültigestopic");

		assertThat(saveDataService.saveData(m1)[0]).isEqualTo("S7_1500/Temperatur/Ist");
		assertThat(saveDataService.saveData(m2)[0]).isEqualTo("S7_1500/Temperatur/Soll");
		assertThat(saveDataService.saveData(m3)[0]).isEqualTo("S7_1500/Temperatur/Differenz");
		assertThat(saveDataService.saveData(m4)[0]).isEqualTo("Wago750/Status");
		assertThat(saveDataService.saveData(m5)).isNull();


	}

	@Test
	void shouldGetJSON() throws Exception {
		mockMvc.perform(get("/S7_1500/soll/latest"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}


}

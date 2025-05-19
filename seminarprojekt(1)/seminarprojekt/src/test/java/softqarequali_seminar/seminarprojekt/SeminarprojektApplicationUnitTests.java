package softqarequali_seminar.seminarprojekt;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
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
		S7_1500_Differenz data = new S7_1500_Differenz("payload", null, newestTS +1);
		S7_1500_Differenz savedData = s7_1500_differenz_repository.save(data);

		String newest_id = s7_1500_differenz_repository.findTopByOrderByTimestampDesc().id;

		assertThat(newest_id).isEqualTo(savedData.id);
	}

	@Test
	void saveDataServiceShouldClassifyTopics(){

		// Mock Message because making the real thing would be very annoying
		Message m = Mockito.mock(Message.class);
		MessageHeaders mh = Mockito.mock(MessageHeaders.class);
		S7_1500_Ist_Repository s7_1500_ist_repository_mock = Mockito.mock(S7_1500_Ist_Repository.class);

		Mockito.when(m.getHeaders()).thenReturn(mh);
		Mockito.when(m.getPayload()).thenReturn("some payload");
		Mockito.when(mh.getTimestamp()).thenReturn(((long) 1));
		Mockito.when(mh.get("mqtt_receivedTopic")).thenReturn("S7_1500/Temperatur/Ist");

		assertThat(saveDataService.saveData(m)[0]).isEqualTo("S7_1500/Temperatur/Ist");

	}

	@Test
	void shouldGetJSON() throws Exception {
		mockMvc.perform(get("/S7_1500/soll/latest"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}


}

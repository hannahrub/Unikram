library ieee;
use ieee.std_logic_1164.all;
-- Add any other standard package you may need
use ieee.numeric_std.all; -- Uncomment if you use the unsigned type

-- Use this entity as a template for your own circuit. Do not change names or sizes
-- You can use unsigned instead of std_logic_vector, but you cannot use both at the same time in this entity

-- das ist ein template für diese seite die das visualisiert.
-- rechte hex anzeige waagerechte panels: hauptstrecke
-- linke anzeige wagrechte panels: nebenstrecke

entity circuit is
port (
  clk500Hz: in std_logic; -- 500 Hz, period 2 ms
  key:     in std_logic_vector(3 downto 0);
  sw:      in std_logic_vector(9 downto 0);
  led:     out std_logic_vector(9 downto 0);
  hex0:    out std_logic_vector(6 downto 0);
  hex1:    out std_logic_vector(6 downto 0));
end circuit;

-- Modify the following architecture to implement your own circuit
architecture description of circuit is
  type state_type is (green, yellow, red, redyellow);
  signal state, next_state : state_type := redyellow; --beginne mit rotgelb
  signal state2, next_state2 : state_type := red; -- beginne mit rotphase
  signal count, count2 : integer range 0 to 13000 := 0;
  
begin
	
	-------------hauptstrasse aka hex0---------------------
	-- 14sgrün, 2s gelb, 14s rot, 2s rotgelb

	-- das hier wechselt nach den jeweiligen phasendauern in den nächsten state
	state_memory: process(clk500Hz) 
	begin 
		if rising_edge(clk500Hz) then
			
			if state = green and count >= 7000 then --14sek vorbei
				count <= 0;
				state <= next_state;
			elsif state = yellow and count >= 1000 then --2 sek vorbei
				count <= 0;
				state <= next_state;
			elsif state = red and count >= 7000 then 
				count <= 0;
				state <= next_state;
			elsif state = redyellow and count >= 1000 then
				count <= 0;
				state <= next_state;
			else
				count <= count + 1;
			end if;
		end if; --rising edge if 
	end process;
	
	---- layout sehr weirde 7 segment anzeige
	-- vector 0123456 0 heisst leuchtet
	-- --6--	rot
	-- 1	    4 
	-- --0--	gelb
	-- 2 	5
	-- --3--	grün
	
	
	-- legt fest welchen output welcher state haben soll. da moore automat ist der output auch nur vom state abhängig
	output_decode: process(state)
	begin
		if state = green then
			hex0 <= "1110111";  --unteres segment
		elsif state = yellow then
			hex0 <= "0111111";  -- mittleres segment
		elsif state = red then
			hex0 <= "1111110"; --oberes segment
		elsif state = redyellow then
			hex0 <= "0111110"; -- oberes und mittleres 
		else
			hex0 <= "1000000"; --zeige null als fehler
		end if;
	end process;
	
	-- übergangslogik
	-- in der vl hängt die auch vom input ab, hier aber nicht da es ja eine festgelegte schaltreihenfolge gibt
	transition_logic: process(state)
	begin
		next_state <= state; --default um latches zu vermeiden
		case(state) is
			when green => 
				next_state <= yellow;
			when yellow =>
				next_state <= red;
			when red =>
				next_state <= redyellow;
			when redyellow =>
				next_state <= green;
			when others =>
				next_state <= red; --rot als default/ was ging schief state
		end case;
	end process;
	
	
	-------------nebenstrasse aka hex1---------------------
	-- 6sgrün, 2s gelb, 22s rot, 2s rotgelb
  
	-- das hier wechselt nach den jeweiligen phasendauern in den nächsten state
	state_memory2: process(clk500Hz) 
	begin 
		if rising_edge(clk500Hz) then
			
			if state2 = green and count2 >= 3000 then --6sek vorbei
				count2 <= 0;
				state2 <= next_state2;
			elsif state2 = yellow and count2 >= 1000 then --2 sek vorbei
				count2 <= 0;
				state2 <= next_state2;
			elsif state2 = red and count2 >= 11000 then --22 sek
				count2 <= 0;
				state2 <= next_state2;
			elsif state2 = redyellow and count2 >= 1000 then
				count2 <= 0;
				state2 <= next_state2;
			else
				count2 <= count2 + 1;
			end if;
		end if; --rising edge if 
	end process;
	
	---- layout sehr weirde 7 segment anzeige
	-- vector 0123456 0 heisst leuchtet
	-- --6--	rot
	-- 1	    4 
	-- --0--	gelb
	-- 2 	5
	-- --3--	grün
	
	
	-- legt fest welchen output welcher state haben soll. da moore automat ist der output auch nur vom state abhängig
	output_decode2: process(state2)
	begin
		if state2 = green then
			hex1 <= "1110111";  --unteres segment
		elsif state2 = yellow then
			hex1 <= "0111111";  -- mittleres segment
		elsif state2 = red then
			hex1 <= "1111110"; --oberes segment
		elsif state2 = redyellow then
			hex1 <= "0111110"; -- oberes und mittleres 
		else
			hex1 <= "1000000"; --zeige null als fehler
		end if;
	end process;
	
	-- übergangslogik
	-- in der vl hängt die auch vom input ab, hier aber nicht da es ja eine festgelegte schaltreihenfolge gibt
	transition_logic2: process(state2)
	begin
		next_state2 <= state2; --default um latches zu vermeiden
		case(state2) is
			when green => 
				next_state2 <= yellow;
			when yellow =>
				next_state2 <= red;
			when red =>
				next_state2 <= redyellow;
			when redyellow =>
				next_state2 <= green;
			when others =>
				next_state2 <= red; --rot als default/ was ging schief state
		end case;
	end process;
end description;
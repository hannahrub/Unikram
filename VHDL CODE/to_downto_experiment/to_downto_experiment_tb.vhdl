library ieee;
use ieee.std_logic_1164.all;

entity to_downto_experiment_tb is
end to_downto_experiment_tb;

architecture test of to_downto_experiment_tb is
	component to_downto_experiment
		port(
		input: in std_logic_vector(3 downto 0); 
		outto: out std_logic_vector(0 to 3); 
		outdownto: out std_logic_vector(3 downto 0)
	);
	end component;
	
	signal input_tb, outdownto_tb : std_logic_vector(3 downto 0);
	signal outto_tb : std_logic_vector(0 to 3);
	
	
begin
	to_dwonto: to_downto_experiment port map (input => input_tb, outdownto=> outdownto_tb, outto => outto_tb);
		
	process 
	
	constant period : time := 1 ns;		--vairable mit wartedauer definieren
	
	begin
		input_tb <= "XXXX";
		wait for period;
		
		input_tb <= "0001";
		wait for period;
		assert(outdownto_tb(0) = '1') report "downto verhält sich anders als ich dachte lol" severity error; --kein fehler
		assert(outto_tb(0) = '0') report "to verhält sich anders als ich dachte lol" severity error; --kein fehler
		--assert(outto_tb = "1000") report "to verhält sich anders als ich dachte lol" severity error; --fehler
		assert(outdownto_tb = "0001") report "downto verhält sich an anders als ich dachte" severity error; --kein fehler
 		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;

-- dh es wird gleich "ausgelesen"/ dieselbe zahl ausgelesen, aber die positionsansprache mit vecor(pos) ist andersherum 
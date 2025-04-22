library ieee;
use ieee.std_logic_1164.all;

-- ALU mit sel signal
-- sel = 0000: a + b
-- sel = 0001: a - b
-- sel = 0010: a and b
-- sel = 0011: a or b
-- sel = 0100: not a
-- sel = 0101: a xor b

entity alu_tb is
end alu_tb;

architecture test of alu_tb is
	component alu
		port(
		a: in std_logic_vector(3 downto 0); 
		b: in std_logic_vector(3 downto 0); 
		sel: in std_logic_vector(3 downto 0);
		result: out std_logic_vector(4 downto 0)
	);
	end component;
	
	signal a_tb, b_tb, sel_tb : std_logic_vector(3 downto 0);
	signal result_tb : std_logic_vector(4 downto 0);
	
begin
	arithmetic_logic_unit: alu port map (a => a_tb, b => b_tb, sel => sel_tb, result => result_tb);
		
	process 
	
	constant period : time := 1 ns;		--vairable mit wartedauer definieren
	
	begin
		a_tb <= "XXXX";
		b_tb <= "XXXX";
		sel_tb <= "XXXX";
		wait for period;
		
		a_tb <= "0010";
		b_tb <= "0001";
		sel_tb <= "0000"; -- +
		wait for period;
 		assert(result_tb = "00011") report "Fehlerhafte Ausgabe bei +" severity error;
		
		sel_tb <= "0001"; -- -
		wait for period;
 		assert(result_tb = "00001") report "Fehlerhafte Ausgabe bei -" severity error;
		
		a_tb <= "0011";
		b_tb <= "0001";
		sel_tb <= "0010"; -- and
		wait for period;
 		assert(result_tb = "00001") report "Fehlerhafte Ausgabe bei and" severity error;
		
		sel_tb <= "0011"; -- or 
		wait for period;
 		assert(result_tb = "00011") report "Fehlerhafte Ausgabe bei or" severity error;
		
		sel_tb <= "0100"; -- not a 
		wait for period;
 		assert(result_tb = "01100") report "Fehlerhafte Ausgabe bei not a" severity error;
		
		sel_tb <= "0101"; -- xor
		wait for period;
 		assert(result_tb = "00010") report "Fehlerhafte Ausgabe bei xor" severity error;
		
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
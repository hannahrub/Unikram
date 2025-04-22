library ieee;
use ieee.std_logic_1164.all;

entity hs_tb is
end hs_tb;

architecture test of hs_tb is
	component hs
		port(
			a: in std_ulogic; 
			b: in std_ulogic; 
			diff: out std_ulogic;  --output
			borrow: out std_ulogic	--carry bit
	);
	end component;
	
	signal a_tb, b_tb, diff_tb, borr_tb : std_ulogic;
	
begin
	half_subtractor: hs port map (a => a_tb, b => b_tb, diff => diff_tb, borrow => borr_tb);
		
	process 
	
	constant period : time := 1 ns;		--vairable mit wartedauer definieren
	
	begin
		a_tb <= 'X';
		b_tb <= 'X';
		wait for period;
		
		a_tb <= '0';
		b_tb <= '0';
		wait for period;
 		assert((diff_tb = '0') and (borr_tb = '0')) report "Fehlerhafte Ausgabe bei 00" severity error;
		
		a_tb <= '0';
		b_tb <= '1';
		wait for period;
		assert((diff_tb = '1') and (borr_tb = '1')) report "Fehlerhafte Ausgabe bei a0 b1" severity error;
		
		a_tb <= '1';
		b_tb <= '0';
		wait for period;
		assert((diff_tb = '1') and (borr_tb = '0')) report "Fehlerhafte Ausgabe bei a1 b0" severity error;
		
		a_tb <= '1';
		b_tb <= '1';
		wait for period;
		assert((diff_tb = '0') and (borr_tb = '0')) report "Fehlerhafte Ausgabe bei 11" severity error;
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
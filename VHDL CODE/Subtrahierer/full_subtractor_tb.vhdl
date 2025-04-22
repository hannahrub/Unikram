library ieee;
use ieee.std_logic_1164.all;

entity fs_tb is
end fs_tb;

architecture test of fs_tb is
	component fs
		port(
			a: in std_ulogic; 
			b: in std_ulogic; 
			borrow_in: in std_ulogic;
			diff: out std_ulogic;  --output
			borrow_out: out std_ulogic	--carry bit
	);
	end component;
	
	signal a_tb, b_tb, borrow_in_tb, diff_tb, borrow_out_tb : std_ulogic;
	
begin
	full_suctractor: fs port map (a => a_tb, b => b_tb, diff => diff_tb, borrow_in => borrow_in_tb, borrow_out => borrow_out_tb);
		
	process 
	
	constant period : time := 1 ns;		--vairable mit wartedauer definieren
	
	begin
		a_tb <= 'X';
		b_tb <= 'X';
		borrow_in_tb <= 'X';
		wait for period;
		
		a_tb <= '0';
		b_tb <= '0';
		borrow_in_tb <= '0';
		wait for period;
 		assert((diff_tb = '0') and (borrow_out_tb = '0')) report "Fehlerhafte Ausgabe bei 000" severity error;
		
		a_tb <= '0';
		b_tb <= '0';
		borrow_in_tb <= '1';
		wait for period;
		assert((diff_tb = '1') and (borrow_out_tb = '1')) report "Fehlerhafte Ausgabe bei 001" severity error;
		
		a_tb <= '0';
		b_tb <= '1';
		borrow_in_tb <= '0';
		wait for period;
		assert((diff_tb = '1') and (borrow_out_tb = '1')) report "Fehlerhafte Ausgabe bei 010" severity error;
		
		a_tb <= '0';
		b_tb <= '1';
		borrow_in_tb <= '1';
		wait for period;
		assert((diff_tb = '0') and (borrow_out_tb = '1')) report "Fehlerhafte Ausgabe bei 011" severity error;
		
		a_tb <= '1';
		b_tb <= '0';
		borrow_in_tb <= '0';
		wait for period;
 		assert((diff_tb = '1') and (borrow_out_tb = '0')) report "Fehlerhafte Ausgabe bei 100" severity error;
		
		a_tb <= '1';
		b_tb <= '0';
		borrow_in_tb <= '1';
		wait for period;
		assert((diff_tb = '0') and (borrow_out_tb = '0')) report "Fehlerhafte Ausgabe bei 101" severity error;
		
		a_tb <= '1';
		b_tb <= '1';
		borrow_in_tb <= '0';
		wait for period;
		assert((diff_tb = '0') and (borrow_out_tb = '0')) report "Fehlerhafte Ausgabe bei 110" severity error;
		
		a_tb <= '1';
		b_tb <= '1';
		borrow_in_tb <= '1';
		wait for period;
		assert((diff_tb = '1') and (borrow_out_tb = '1')) report "Fehlerhafte Ausgabe bei 111" severity error;
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
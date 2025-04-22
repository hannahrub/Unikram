library ieee;
use ieee.std_logic_1164.all;

entity nbit_subtrahierer_tb is
end nbit_subtrahierer_tb;

architecture test of nbit_subtrahierer_tb is
	component n_subtr
	generic(n: integer := 4);
		port(
			a: in std_logic_vector(n-1 downto 0); 
			b: in std_logic_vector(n-1 downto 0); 
			diff: out std_logic_vector(n-1 downto 0);
			borrow_out: out std_logic
	);
	end component;
	
	signal a_tb : std_logic_vector(3 downto 0);
	signal b_tb: std_logic_vector(3 downto 0);
	signal diff_tb : std_logic_vector(3 downto 0);
	signal borrow_out_tb : std_logic;
	
begin
	uut: n_subtr port map(a => a_tb, b => b_tb, diff => diff_tb, borrow_out => borrow_out_tb);
		
	process 
	
	constant period : time := 1 ns;		--vairable mit wartedauer definieren
	
	begin
		a_tb <= "0000";
		b_tb <= "0000";
		wait for period;
		
		a_tb <= "0001";
		b_tb <= "0001";
		wait for period;
		
		a_tb <= "0010";
		b_tb <= "0010";
		wait for period;
		
		a_tb <= "0100";
		b_tb <= "0100";
		wait for period;
		
		a_tb <= "1000";
		b_tb <= "1000";
		wait for period;
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
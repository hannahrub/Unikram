library ieee;
use ieee.std_logic_1164.all;

entity priodecoder_tb is
end priodecoder_tb;

architecture test of priodecoder_tb is
	component priodecoder
		port(
			i: in std_logic_vector(6 downto 0); 
			o_0: out std_logic;
			o_1: out std_logic;
			o_2: out std_logic
		);
	end component;
	 
signal a : std_logic_vector(6 downto 0);
signal r_0,  r_1, r_2 : std_logic;

begin
	prio_dc: priodecoder port map (i => a, o_0 => r_0, o_1 => r_1, o_2 => r_2);
	process begin
	a <= "0000000";
	wait for 1 ns;
	a <= "0000001";
	wait for 1 ns;
	a <= "0000011";
	wait for 1 ns;
	a <= "0000111";
	wait for 1 ns;
	a <= "0001111";
	wait for 1 ns;
	a <= "0011111";
	wait for 1 ns;
	a <= "0111111";
	wait for 1 ns;
	a <= "1111111";
	wait for 1 ns;
	
	assert false report "Reached end of test";
	wait;
	end process;
	end test;


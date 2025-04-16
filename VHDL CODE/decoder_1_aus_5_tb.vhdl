library ieee;
use ieee.std_logic_1164.all;

entity decoder_1_aus_5_tb is
end decoder_1_aus_5_tb;

architecture test of decoder_1_aus_5_tb is
	component decoder_1_aus_5
		port(
			i: in std_logic_vector(2 downto 0); --ist das die richtige anzahl an eingangsbits? wir müssen ja eig nur 5 darstellen können
			o_0: out std_logic;
			o_1: out std_logic;
			o_2: out std_logic;
			o_3: out std_logic;
			o_4: out std_logic
			);
	end component;
	 
signal a : std_logic_vector(2 downto 0);
signal r_0,  r_1, r_2, r_3, r_4 : std_logic;

begin
	dc: decoder_1_aus_5 port map (i => a, o_0 => r_0, o_1 => r_1, o_2 => r_2, o_3 => r_3, o_4 => r_4);
	process begin
	a <= "000";
	wait for 1 ns;
	a <= "001";
	wait for 1 ns;
	a <= "010";
	wait for 1 ns;
	a <= "011";
	wait for 1 ns;
	a <= "100";
	wait for 1 ns;
	
	assert false report "Reached end of test";
	wait;
	end process;
	end test;


library ieee;
use ieee.std_logic_1164.all;

entity decoder_1_aus_5 is
	port(
	i: in std_logic_vector(2 downto 0); --ist das die richtige anzahl an eingangsbits? wir müssen ja eig nur 5 darstellen können
	o_0: out std_logic;
	o_1: out std_logic;
	o_2: out std_logic;
	o_3: out std_logic;
	o_4: out std_logic
	);
end decoder_1_aus_5;
 
 architecture behave of decoder_1_aus_5 is
 begin
	o_0 <= '1' when (i = "000") else '0';
	o_1 <= '1' when (i = "001") else '0';
	o_2 <= '1' when (i = "010") else '0';
	o_3 <= '1' when (i = "011") else '0';
	o_4 <= '1' when (i = "100") else '0';
end behave;
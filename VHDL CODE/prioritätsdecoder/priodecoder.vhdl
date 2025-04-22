library ieee;
use ieee.std_logic_1164.all;

entity priodecoder is
	port(
	i: in std_logic_vector(6 downto 0); 
	o_0: out std_logic;
	o_1: out std_logic;
	o_2: out std_logic
	);
end priodecoder;
 
 architecture behave of priodecoder is
 begin
	o_0 <= '1' when ((i = "0000001")or (i = "0000111") or (i = "0011111") or (i = "1111111")) else '0';
	o_1 <= '1' when ((i = "0000011")or (i = "0000111") or (i = "0111111") or (i = "1111111")) else '0';
	o_2 <= '1' when ((i = "0001111")or (i = "0011111") or (i = "0111111") or (i = "1111111")) else '0';
 end behave;
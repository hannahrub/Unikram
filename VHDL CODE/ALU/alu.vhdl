library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.ALL; --damit kann man vectoren in ints casten

-- ALU mit sel signal
-- sel = 0000: a + b
-- sel = 0001: a - b
-- sel = 0010: a and b
-- sel = 0011: a or b
-- sel = 0100: not a
-- sel = 0101: a xor b

entity alu is 
	port(
		a: in std_logic_vector(3 downto 0); 
		b: in std_logic_vector(3 downto 0); 
		sel: in std_logic_vector(3 downto 0);
		result: out std_logic_vector(4 downto 0) --einen mehr damit es bei der addition keinen überlauf gibt
	);
end alu;
 
 architecture behave of alu is
 begin
	 with sel select
		result <= std_logic_vector(to_unsigned(to_integer(signed(a)) + to_integer(signed(b)), 5)) when "0000",
				  std_logic_vector(to_unsigned(to_integer(signed(a)) - to_integer(signed(b)), 5)) when "0001",
				  '0' & (a and b) when "0010",
				  '0' & (a or b)  when "0011",
				  '0' & (not a)  	when "0100",
				  '0' & (a xor b) when "0101",
				  "00000" when others;
end behave;
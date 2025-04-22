library ieee;
use ieee.std_logic_1164.all;

entity half_adder is
	port(
		a, b: in std_logic;
		sum, carry: out std_logic);
-- Inputs: a,b: input bits to be added
-- Outputs: sum: output bit representing the sum of a and b
-- carry: output bit representing the carry-out of the addition
end half_adder;

architecture data of half_adder is
	begin
	sum <= a xor b; -- Calculate the sum using XOR gate
	carry <= a and b; -- Calculate the carry using AND gate
end data;
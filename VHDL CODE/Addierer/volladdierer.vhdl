library ieee; 
use ieee.std_logic_1164.all;

entity full_adder is
	port(
	a,b,carry_in: in std_logic;
	sum, carry_out: out std_logic);
	
-- Inputs: a,b: input bits to be added
-- carry_in: input bit representing the carry-in of the addition
-- Outputs: sum: output bit representing the sum of a, b and cin
-- carry_out: output bit representing the carry-out of the addition

end full_adder;


architecture data of full_adder is
	component half_adder
		port( 
			a, b: in std_logic;
			sum, carry: out std_logic);
	end component;

	signal s1,s2,c1: std_logic;

begin
-- Calculate the sum and carry-out using two half adders
ha1: half_adder port map(a=>a, b=>b, sum=>s1, carry=>c1);
ha2: half_adder port map(a=>s1, b=>carry_in, sum=>sum, carry=>s2);
carry_out <= c1 or s2; -- Calculate the final carry-out using OR gate
end data; 

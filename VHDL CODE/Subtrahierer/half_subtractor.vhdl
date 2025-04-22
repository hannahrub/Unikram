library ieee;
use ieee.std_logic_1164.all;

entity hs is
	port(
	a: in std_ulogic; 
	b: in std_ulogic; 
	diff: out std_ulogic;  --output
	borrow: out std_ulogic	--carry bit
	);
end hs;
 
 architecture behave of hs is
 begin
	diff <= a xor b;
	borrow <= (not a) and b;
end behave;
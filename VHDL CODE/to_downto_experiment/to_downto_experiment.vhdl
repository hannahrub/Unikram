library ieee;
use ieee.std_logic_1164.all;

entity to_downto_experiment is 
	port(
		input: in std_logic_vector(3 downto 0); 
		outto: out std_logic_vector(0 to 3); 
		outdownto: out std_logic_vector(3 downto 0)
	);
end to_downto_experiment;
 
 architecture behave of to_downto_experiment is
 begin
	 outto <= input;
	 outdownto <= input;
end behave;
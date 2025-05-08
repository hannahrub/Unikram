library ieee; 
use ieee.std_logic_1164.all;

--1 aus 2 multiplexer

entity mux is
	port(
		D : in std_logic; --da werden die Daten angelegt, entspricht x1
		I : in std_logic; -- x0
		control : in std_logic; --ich glaube das entspricht enable
		output : out std_logic
	);

end mux;


architecture behave of mux is

begin
	with control select
		output <= I when '0',
				D when '1',
				'X' when others;

end behave; 
library ieee;
use ieee.std_logic_1164.all;

entity fs is --full subtractor / voll subtrahirer
	port(
	a: in std_ulogic; 
	b: in std_ulogic; 
	borrow_in: in std_ulogic;
	diff: out std_ulogic;  --output
	borrow_out: out std_ulogic	--carry bit
	);
end fs;
 
 architecture behave of fs is
	component hs  
		port(
			a: in std_ulogic; 
			b: in std_ulogic; 
			diff: out std_ulogic;  --output
			borrow: out std_ulogic	--carry bit
		);
	end component;
	
	signal s1, s2, b1 : std_ulogic;
	
 begin
 -- vollsubtraktion indem zwei halbsubtrahierer benutzt werden
	hs1: hs port map(a => a, b => b, diff => s1, borrow => b1); -- hier rechnen wir a-b
	hs2: hs port map(a => s1, b => borrow_in, diff => diff, borrow => s2); -- hier rechnen wir ergebnis von hs1 - übertrag der letzten rechnung und geben auf 
	borrow_out <= b1 or s2; -- falls noch was übrig ist
end behave;
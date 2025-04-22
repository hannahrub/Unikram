library ieee;
use ieee.std_logic_1164.all;

entity nbit_vollsubtrahierer is -- voll subtrahierer für beliebige amzahl von eingangsbits mithilfe des keywortes generic und generate
	generic(n: integer := 4); -- setze n = 4 als standard
	port(
		a: in std_logic_vector(n-1 downto 0); 
		b: in std_logic_vector(n-1 downto 0); 
		diff: out std_logic_vector(n-1 downto 0);
		borrow_out: out std_logic
	);
end nbit_vollsubtrahierer;
 
 architecture behave of nbit_vollsubtrahierer is
	component fs  
		port(
			a: in std_ulogic; 
			b: in std_ulogic; 
			borrow_in: in std_ulogic;
			diff: out std_ulogic;  --output
			borrow_out: out std_ulogic	--carry bit
	);
	end component;
	
	signal t: std_logic_vector(n downto 0);
	
 begin
	t(0) <= '0'; 	--initialisiere das null bit sodass borrow_in = 0
	borrow_out <= t(n); 		-- initialisiere die andere seite des t-busses

	schleife: for i in 0 to n-1 generate
		schleife_i: fs port map(a(i), b(i), t(i), diff(i), t(i+1));
	end generate;
		
end behave;
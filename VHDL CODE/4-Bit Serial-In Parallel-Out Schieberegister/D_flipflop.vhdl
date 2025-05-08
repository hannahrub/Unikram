library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.ALL; --damit kann man vectoren in ints casten

-- 4 bit in out schieberegister, bestehend aus 4 hintereinander geschalteten D flipflops die alle ans selbe clock signal angeschlossen sind

entity d_flipflop is 
	port(
		D: in std_logic; 
		clk: in std_logic; 
		clear : in std_logic;
		Q: out std_logic;
		notQ: out std_logic 
	);
end d_flipflop;

 architecture behave of d_flipflop is
 begin
	process (clk)
	begin
		if clear = '1' then Q <= 'X'; 
		elsif rising_edge(clk) then Q <= D; --daten bei rising edge übernehmen
		end if;
	end process;
end behave;
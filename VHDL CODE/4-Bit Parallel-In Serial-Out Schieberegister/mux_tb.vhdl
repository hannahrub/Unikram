library ieee;
use ieee.std_logic_1164.all;

entity mux_tb is
end mux_tb;

architecture test of mux_tb is
	component mux is
		port(
			D : in std_logic; --da werden die Daten angelegt, entspricht x1
			I : in std_logic; -- x0
			control : in std_logic; --ich glaube das entspricht enable
			output : out std_logic
		);
	end component;
	
	
	signal x0, x1, output, control : std_ulogic;
	
begin
	-- port map (componente => signal)
	multiplexer : mux port map(I => x0, D => x1, control => control, output => output);
		
	process begin
	-- erst alles clearen
		x0 <= 'X';
		x1 <= 'X';
		control <= 'X';
		wait for 1 ns;
		
	-- jetzt gehts los
		x0 <= '0';
		x1 <= '1';
		control <= '0';
		wait for 1 ns;
		
		control <= '1';
		wait for 1 ns;
		
		x0 <= '1';
		x1 <= '0';
		control <= '0';
		wait for 1 ns;
		
		control <= '1';
		wait for 1 ns;
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
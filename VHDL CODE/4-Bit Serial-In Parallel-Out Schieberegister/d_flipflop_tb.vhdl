library ieee;
use ieee.std_logic_1164.all;

entity d_flipflop_tb is
end d_flipflop_tb;

architecture test of d_flipflop_tb is
	component d_flipflop is 
		port(
			D: in std_logic; 
			clk: in std_logic; 
			clear : in std_logic;
			Q: out std_logic;
			notQ: out std_logic 
		);
	end component;
	
	signal data, clock, output, clear : std_ulogic;
	
begin
	-- port map (componente => signal)
	flipflop : d_flipflop port map(D => data, clk => clock, clear => clear, Q => output);
		
	process begin
	-- erst alles clearen
		clear <= '1';
		data <= 'X';
		clock <= 'X';
		wait for 1 ns;
		
	-- jetzt gehts los
		clear <= '0';
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		data <= '1';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		data <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
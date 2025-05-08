library ieee;
use ieee.std_logic_1164.all;

entity schieberegister_4_bit_tb is
end schieberegister_4_bit_tb;

architecture test of schieberegister_4_bit_tb is
	component schieberegister_4_bit
		port(
			input_data : in std_logic;
			clk : in std_logic;
			clear : in std_logic;
			Q3 : out std_logic
		);
	end component;
	
	
	signal data, clock, output, clear : std_ulogic;
	
begin
	-- port map (componente => signal)
	schieberegister : schieberegister_4_bit port map(input_data => data, clk => clock, clear => clear, Q3 => output);
		
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
		
		data <= '1';
		clock <= '0';
		clear <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';
		wait for 1 ns;
		
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
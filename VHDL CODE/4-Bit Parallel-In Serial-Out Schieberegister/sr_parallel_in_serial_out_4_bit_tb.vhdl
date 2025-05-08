library ieee;
use ieee.std_logic_1164.all;

entity sr_parallel_in_serial_out_4_bit_tb is
end sr_parallel_in_serial_out_4_bit_tb;

architecture test of sr_parallel_in_serial_out_4_bit_tb is
	component sr_parallel_in_serial_out_4_bit is
		port(
			D : in std_logic_vector(3 downto 0);
			mux_in : in std_logic;
			control : in std_logic; --ich glaube das entspricht enable
			clk : in std_logic;
			output : out std_logic
		);
	end component;
	
	
	signal clock, output, control, mux_in : std_ulogic;
	signal data : std_logic_vector(3 downto 0);
	
begin
	-- port map (componente => signal)
	schieberegister : sr_parallel_in_serial_out_4_bit port map(D => data, mux_in => mux_in, control => control, clk => clock, output => output);
		
	process begin
	-- erst alles clearen
		control <= 'X';
		data <= "XXXX";
		clock <= 'X';
		mux_in <= 'X';
		wait for 1 ns;
		
	-- initialisieren
		control <= '0';
		data <= "0001";
		mux_in <= '0';
		clock <= '0';
		wait for 1 ns;
		
	--los gehts
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		control <= '1'; -- lade daten (wähle D_i im mux mit '1')
		wait for 1 ns;
		
		clock <= '1'; -- gib erstes bit aus
		wait for 1 ns;
		
		clock <= '0';
		control <= '0'; --Daten sind geladen jetzt verwende geladenes im mux
		wait for 1 ns;
		
		clock <= '1'; --gibt 2. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';--gib 3. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1'; --gib 4. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		
		----2. runde 
		-- initialisieren
		control <= '0';
		data <= "0100";
		mux_in <= '0';
		clock <= '0';
		wait for 1 ns;
		
	--los gehts
		clock <= '1';
		wait for 1 ns;
		
		clock <= '0';
		control <= '1'; -- lade daten (wähle D_i im mux mit '1')
		wait for 1 ns;
		
		clock <= '1'; -- gib erstes bit aus
		wait for 1 ns;
		
		clock <= '0';
		control <= '0'; --Daten sind geladen jetzt verwende geladenes im mux
		wait for 1 ns;
		
		clock <= '1'; --gibt 2. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1';--gib 3. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		clock <= '1'; --gib 4. bit aus
		wait for 1 ns;
		
		clock <= '0';
		wait for 1 ns;
		
		
		assert false report "reached end of test";
		wait;
		
	end process;
end test;
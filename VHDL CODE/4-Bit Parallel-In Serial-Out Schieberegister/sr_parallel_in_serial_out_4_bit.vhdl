library ieee; 
use ieee.std_logic_1164.all;

-- daten werden gleichzeitig (parallel) eingegeben
-- dann werden sie bit für bit durch clk getaktet ausgegeben
-- man lädt am anfang einmal daten mit control = '1' und stellt zum durchschieben dann auf '0'
-- ich glaube die mux ist nur dafür da ein enable zu ermöglichen

entity sr_parallel_in_serial_out_4_bit is
	port(
		D : in std_logic_vector(3 downto 0);
		mux_in : in std_logic;
		control : in std_logic; --ich glaube das entspricht enable
		clk : in std_logic;
		output : out std_logic
	);

end sr_parallel_in_serial_out_4_bit;


architecture behave of sr_parallel_in_serial_out_4_bit is
	component d_flipflop is 
		port(
			D: in std_logic; 
			clk: in std_logic; 
			clear : in std_logic;
			Q: out std_logic;
			notQ: out std_logic --brauchen wir hier nicht, wird auch nicht angeschlossen
		);
	end component;
	
	component mux is
		port(
			D : in std_logic; --da werden die Daten angelegt, entspricht x1
			I : in std_logic; -- x0
			control : in std_logic; --ich glaube das entspricht enable
			output : out std_logic
		);
	end component;

	signal q0, q1, q2 : std_logic; --flipflop outputs
	signal m0, m1, m2, m3 : std_logic; --mux outputs
	signal c : std_logic; --wir müssem clear mit irgendwas verbinden. selbst wenn das nix macht

begin
	 c <= '0'; --dafür sorgen dass clear vom flipflop auf jeden fall disabled ist
	-- port map(componente => signal)
	mux0: mux port map(D => D(0), I => mux_in, control => control, output => m0);
	ff0: d_flipflop port map(D => m0, clk => clk, clear =>c, Q => q0);
	mux1: mux port map(D => D(1), I => q0, control => control, output => m1);
	ff1: d_flipflop port map(D => m1, clk => clk, clear =>c, Q => q1);
	mux2: mux port map(D => D(2), I => q1, control => control, output => m2);
	ff2: d_flipflop port map(D => m2, clk => clk, clear =>c, Q => q2);
	mux3: mux port map(D => D(3), I => q2, control => control, output => m3);
	ff3: d_flipflop port map(D => m3, clk => clk, clear =>c, Q => output);

end behave; 
library ieee; 
use ieee.std_logic_1164.all;

entity schieberegister_4_bit is
	port(
		input_data : in std_logic;
		clk : in std_logic;
		clear : in std_logic;
		Q3 : out std_logic
	);

end schieberegister_4_bit;


architecture behave of schieberegister_4_bit is
	component d_flipflop is 
		port(
			D: in std_logic; 
			clk: in std_logic; 
			clear : in std_logic;
			Q: out std_logic;
			notQ: out std_logic --brauchen wir hier nicht, wird auch nicht angeschlossen
		);
	end component;

	signal q0, q1, q2: std_logic;

begin
	-- port map(componente => signal)
	ff0: d_flipflop port map(D => input_data, clk => clk, clear => clear, Q => q0);
	ff1: d_flipflop port map(D => q0, clk => clk, clear => clear, Q => q1);
	ff2: d_flipflop port map(D => q1, clk => clk, clear => clear, Q => q2);
	ff3: d_flipflop port map(D => q2, clk => clk, clear => clear, Q => Q3);

end behave; 
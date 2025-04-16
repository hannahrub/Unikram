library ieee;
use ieee.std_logic_1164.all;
use IEEE.numeric_std.all;

entity fakultaet is
	port(
	s: in std_logic; --sagt 'los'
	i: in unsigned; -- zahleingabe
	o: out unsigned --zahloutput
	);
end fakultaet;
 
architecture behave of decoder_1_aus_5 is

signal i_number : unsigned(3 downto 0);
signal o_number : unsigned(3 downto 0);


begin
	process(s)
	variable result : unsigned(3 downto 0);
	
	begin
		i_number <= i;
		o <= o_number;
		
		result <= 1;
		schleife: for n in 1 to i_number loop
			result := result * n;
		end loop schleife;
		
		o_number <= result;
	end process;
	
	
end behave;



--- googles lösung:----
entity fakultät is
    port (
        input_zahl : in std_logic_vector(7 downto 0); -- Beispiel: 8-Bit Eingabedaten
        clk : in std_logic;
        reset : in std_logic;
        valid : out std_logic;
        output_fakultaet : out std_logic_vector(15 downto 0) -- Beispiel: 16-Bit Ausgang
    );
end entity;

architecture behavioral of fakultät is
    type state_type is (IDLE, MULTIPLY);
    signal current_state : state_type := IDLE;
    signal i : integer := 0;
    signal fakultaet_wert : unsigned(15 downto 0) := (others => '0');
    signal input_zahl_wert : unsigned(7 downto 0);
begin
    process (clk, reset)
    begin
        if (reset = '1') then
            current_state <= IDLE;
            i <= 0;
            fakultaet_wert <= (others => '0');
            valid <= '0';
        elsif (rising_edge(clk)) then
            case current_state is
                when IDLE =>
                    if (input_zahl_wert /= (others => '0')) then -- Überprüfen, ob ein neuer Wert bereit ist
                        current_state <= MULTIPLY;
                        i <= 1;
                        fakultaet_wert <= (others => '0');
                        input_zahl_wert <= unsigned(input_zahl);
                        valid <= '0';
                    end if;
                when MULTIPLY =>
                    if (i <= unsigned(input_zahl_wert)) then -- Schleife solange i kleiner oder gleich input_zahl ist
                        fakultaet_wert <= fakultaet_wert + unsigned(input_zahl_wert);
                        i <= i + 1;
                        valid <= '0';
                    else
                        current_state <= IDLE;
                        valid <= '1';
                    end if;
            end case;
        end if;
    end process;

    output_fakultaet <= std_logic_vector(fakultaet_wert);
end architecture;
-- Default VHDL example template for the VHDL DE10 board simulator
library ieee;
use ieee.std_logic_1164.all;
-- Add any other standard package you may need
-- use ieee.numeric_std.all; -- Uncomment if you use the unsigned type

-- Use this entity as a template for your own circuit. Do not change names or sizes
-- You can use unsigned instead of std_logic_vector, but you cannot use both at the same time in this entity
entity circuit is
port (
  clk500Hz: in std_logic; -- 50 Hz, period 20 ms
  key:     in std_logic_vector(3 downto 0);
  sw:      in std_logic_vector(9 downto 0);
  led:     out std_logic_vector(9 downto 0);
  hex0:    out std_logic_vector(6 downto 0);
  hex1:    out std_logic_vector(6 downto 0));
end circuit;

-- Modify the following architecture to implement your own circuit
architecture description of circuit is
  signal lowerbits: std_logic_vector(3 downto 0);
  signal upperbits: std_logic_vector(3 downto 0);
  signal out_0 : std_logic_vector(6 downto 0);
  signal out_1 : std_logic_vector(6 downto 0);
  
begin
	lowerbits <= sw(3 downto 0); --unterste 4 bit
	upperbits <= sw(7 downto 4); --obere 4 bit unsrer 8 bit binärzahl
	
	
	with lowerbits select
	out_0 <= "1000000" when "0000", -- zeichnet 0 auf der anzeige
			"1111001" when "0001", 	-- zei 1 auf der anzeige
			"0100100" when "0010", 
			"0110000" when "0011",
			"0011001" when "0100",
			"0010010" when "0101",
			"0000010" when "0110",
			"1011000" when "0111",
			"0000000" when "1000",
			"0010000" when "1001",
			"0001000" when "1010", -- A anzeigen
			"0000011" when "1011", -- B darstellen
			"1000110" when "1100",
			"0100001" when "1101",
			"0000110" when "1110",
			"0001110" when "1111",
			"1000000" when others;
			
	with upperbits select
	out_1 <= "1000000" when "0000", -- zeichnet 0 auf der anzeige
			"1111001" when "0001", 	-- zei 1 auf der anzeige
			"0100100" when "0010", 
			"0110000" when "0011",
			"0011001" when "0100",
			"0010010" when "0101",
			"0000010" when "0110",
			"1011000" when "0111",
			"0000000" when "1000",
			"0010000" when "1001",
			"0001000" when "1010", -- A anzeigen
			"0000011" when "1011", -- B darstellen
			"1000110" when "1100",
			"0100001" when "1101",
			"0000110" when "1110",
			"0001110" when "1111",
			"1000000" when others;
	
	hex0 <= out_0;
	hex1 <= out_1;
    
end description;
library ieee;
use ieee.std_logic_1164.all;

entity adder_nbit_tb is
end adder_nbit_tb;

architecture behavioral of adder_nbit_tb is
	component adder_nbit
	generic(n: integer := 8);
	
	port(
		a,b : in std_logic_vector(n-1 downto 0);
		sum : out std_logic_vector(n-1 downto 0);
		carry_out : out std_logic);
	end component;
	
	signal a,b,sum : std_logic_vector(7 downto 0);
	signal c_out : std_logic;
	
begin
	uut : adder_nbit port
	map(a,b,sum=>sum,carry_out=>c_out);
	process
		begin
		-- ich soll die zahlen 120 und -78 im zweierkomplement addieren
		-- zweikomplement heisst hier7 bits betrag und 8 vorzeichenbit
		a <= "01111000"; 
		b <= "11001110";
		wait for 1 ns;
		assert(sum = "00101010") report "es kam nicht 42 raus" severity error;
		wait;
	end process;
end behavioral;

--es kam 46 und carry_out = 1 raus
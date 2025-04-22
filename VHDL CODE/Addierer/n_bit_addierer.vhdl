library ieee; 
use ieee.std_logic_1164.all;

entity adder_nbit is
	generic(n : integer := 4); -- Hier gilt der Standardwert n=4
	
	port( 
		a,b : in std_logic_vector(n-1 downto 0);
		sum : out std_logic_vector(n-1 downto 0);
		carry_out : out std_logic);
	end adder_nbit;
	
architecture structural of adder_nbit is
	component full_adder
		port(
			a,b,carry_in : in std_logic;
		sum,carry_out : out std_logic);
	end component;
	
	signal t: std_logic_vector(n downto 0);

begin
	t(0) <= '0'; -- Initialisiere das Null-Bit --> Carry_in gleich Null
	carry_out <= t(n); -- Die andere Seite des t-Busses --> Carry_out gleich t(n)
	
	FA: for i in 0 to n-1 generate -- For-Schleife mit generate
		FA_i: full_adder port map (a(i), b(i), t(i), sum(i),t(i+1));
	end generate;
end; 
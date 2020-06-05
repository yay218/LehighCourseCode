// -------------------------------------------------------------------------
/**
 * This class provides a single constant definition that represents a timeline
 * of events in the history of computing. The timeline is represented in the
 * form of an array of {@link HistoricEvent} objects. This timeline is taken
 * from the "Computer History Timeline" at:
 * http://www.history-timelines.org.uk/events-timelines/07-computer-
 * history-timeline.htm. A more definitive, interactive timeline is available
 * from the Computer History Museum at:
 * http://www.computerhistory.org/timeline/.
 *
 * @author Stephen Edwards
 * @version 2010.11.10
 */
public class HistoricEvents
{
    // ----------------------------------------------------------
    /**
     * The timeline of events in the history of computing.
     */
    public static final HistoricEvent[] TIMELINE = { new HistoricEvent(
        60,
        "Heron of Alexandria",
        "Heron of Alexandria invents machines which follow a series "
            + "of instructions"),

        new HistoricEvent(
            724,
            "Liang Ling-Can",
            "Liang Ling-Can invents the first fully mechanical clock"),

        new HistoricEvent(
            1492,
            "Leonardo da Vinci",
            "Drawings by Leonardo da Vinci depict inventions such as "
                + "flying machines, including a helicopter, the first "
                + "mechanical calculator and one of the first programmable "
                + "robots"),

        new HistoricEvent(
            1614,
            "John Napier",
            "John Napier invents a system of moveable rods (Napier's "
                + "Rods) based on logarithms which was able to multiply, "
                + "divide and calculate square and cube roots"),

        new HistoricEvent(
            1622,
            "William Oughtred",
            "William Oughtred develops slide rules"),

        new HistoricEvent(
            1623,
            "Calculating Clock",
            "Invented by Wilhelm Schickard"),

        new HistoricEvent(
            1642,
            "Blaise Pascal",
            "Blaise Pascal invents the the \"Pascaline\", a mechanical "
                + "adding machine"),

        new HistoricEvent(
            1671,
            "Gottfried Leibniz",
            "Gottfried Leibniz is known as one of the founding fathers "
                + "of calculus"),

        new HistoricEvent(
            1801,
            "Joseph-Marie Jacquard",
            "Joseph-Marie Jacquard invents an automatic loom controlled "
                + "by punched cards"),

        new HistoricEvent(
            1820,
            "Arithmometer",
            "The Arithmometer was the first mass-produced calculator "
                + "invented by Charles Xavier Thomas de Colmar"),

        new HistoricEvent(
            1822,
            "Charles Babbage",
            "Charles Babbage designs his first mechanical computer"),

        new HistoricEvent(
            1834,
            "Analytical Engine",
            "The Analytical Engine was invented by Charles Babbage"),

        new HistoricEvent(
            1835,
            "Morse Code",
            "Samuel Morse invents Morse code"),

        new HistoricEvent(
            1848,
            "Boolean Algebra",
            "Boolean algebra is invented by George Boole"),

        new HistoricEvent(
            1853,
            "Tabulating Machine",
            "Per Georg Scheutz and his son Edvard invent the"
                + "Tabulating Machine"),

        new HistoricEvent(
            1869,
            "William Stanley Jevons",
            "William Stanley Jevons designs a practical logic machine"),

        new HistoricEvent(
            1878,
            "Ramon Verea",
            "Ramon Verea invents a fast calculator with an internal "
                + "multiplication table"),

        new HistoricEvent(
            1880,
            "Alexander Graham Bell",
            "Alexander Graham Bell invents the telephone called the"
                + "Photophone"),

        new HistoricEvent(
            1884,
            "Comptometer",
            "The Comptometer is an invention of Dorr E. Felt which is "
                + "operated by pressing keys"),

        new HistoricEvent(
            1890,
            "Herman Hollerith",
            "Herman Hollerith invents a counting machine which increment "
                + "mechanical counters"),

        new HistoricEvent(
            1895,
            "Guglielmo Marconi",
            "Radio signals were invented by Guglielmo Marconi"),

        new HistoricEvent(
            1896,
            "Tabulating Machine Company",
            "Herman Hollerith forms the Tabulating Machine Company which "
                + "later becomes IBM"),

        new HistoricEvent(
            1898,
            "Nikola Tesla",
            "Remote control was invented by  Nikola Tesla"),

        new HistoricEvent(
            1906,
            "Lee De Forest",
            "Lee De Forest invents the electronic tube"),

        new HistoricEvent(1911, "IBM", "IBM is formed on June 15, 1911"),

        new HistoricEvent(
            1923,
            "Philo Farnsworth",
            "Television Electronic was invented by  Philo Farnsworth"),

        new HistoricEvent(
            1924,
            "John Logie Baird",
            "Electro Mechanical television system was invented by "
                + "John Logie Baird"),

        new HistoricEvent(
            1924,
            "Walther Bothe",
            "Walther Bothe develops the logic gate"),

        new HistoricEvent(
            1930,
            "Vannevar Bush",
            "Vannevar Bush develops a partly electronic Difference Engine"),

        new HistoricEvent(
            1931,
            "Kurt Godel",
            "Kurt Godel publishes a paper on the use of a universal "
                + "formal language"),

        new HistoricEvent(
            1937,
            "Alan Turing",
            "Alan Turing develops the concept of a theoretical "
                + "computing machine"),

        new HistoricEvent(
            1938,
            "Konrad Zuse",
            "Konrad Zuse creates the Z1 Computer a binary digital computer "
                + "using punch tape"),

        new HistoricEvent(
            1939,
            "George Stibitz",
            "George Stibitz develops the Complex Number Calculator--a "
                + "foundation for digital computers"),

        new HistoricEvent(
            1939,
            "Hewlett Packard",
            "William Hewlett and David Packard start Hewlett Packard"),

        new HistoricEvent(
            1939,
            "John Vincent Atanasoff and Clifford Berry",
            "John Vincent Atanasoff and Clifford Berry develop the "
                + "ABC (Atanasoft-Berry Computer) prototype"),

        new HistoricEvent(
            1943,
            "Colossus",
            "Alan Turing develops the the code-breaking machine Colossus"),

        new HistoricEvent(
            1943,
            "Enigma",
            "Adolf Hitler uses the Enigma encryption machine"),

        new HistoricEvent(
            1944,
            "Howard Aiken & Grace Hopper",
            "Howard Aiken and Grace Hopper designed the MARK series of "
                + "computers at Harvard University"),

        new HistoricEvent(
            1945,
            "Computer Bug",
            "The term computer �bug� as computer bug was first used by "
                + "Grace Hopper"),

        new HistoricEvent(
            1945,
            "ENIAC",
            "John Presper Eckert & John W. Mauchly: John Presper Eckert & "
                + "John W. Mauchly develop the ENIAC (Electronic Numerical "
                + "Integrator and Computer)"),

        new HistoricEvent(
            1946,
            "F.C. Williams",
            "F.C. Williams develops his cathode-ray tube (CRT) storing "
                + "device the forerunner to  random-access memory (RAM)"),

        new HistoricEvent(
            1947,
            "Douglas Engelbart",
            "Douglas Engelbart theorises on interactive computing with "
                + "keyboard and screen display instead of on punchcards"),

        new HistoricEvent(
            1947,
            "Pilot ACE",
            "Donald Watts Davies joins Alan Turing to build the fastest "
                + "digital computer in England at the time, the Pilot ACE"),

        new HistoricEvent(
            1947,
            "William Shockley",
            "William Shockley invents the transistor at Bell Labs"),

        new HistoricEvent(
            1948,
            "Andrew Donald Booth",
            "Andrew Donald Booth invents magnetic drum memory"),

        new HistoricEvent(
            1948,
            "Frederic Calland Williams  & Tom Kilburn",
            "Frederic Calland Williams  & Tom Kilburn develop the SSEM "
                + "\"Small Scale Experimental Machine\" digital CRT storage "
                + "which was soon nicknamed the \"Baby\""),

        new HistoricEvent(
            1949,
            "Claude Shannon",
            "Claude Shannon builds the first machine that plays chess"),

        new HistoricEvent(
            1949,
            "Howard Aiken",
            "Howard Aiken develops the Harvard-MARK III"),

        new HistoricEvent(
            1950,
            "Alan Turing",
            "Alan Turing publishes his paper Computing Machinery and "
                + "Intelligence which helps create the Turing Test"),

        new HistoricEvent(
            1950,
            "Hideo Yamachito",
            "The first electronic computer is created in Japan by "
                + "Hideo Yamachito."),

        new HistoricEvent(
            1951,
            "EDVAC",
            "The EDVAC (Electronic Discrete Variable Automatic Computer) "
                + "begins performing basic tasks. Unlike the ENIAC, it was "
                + "binary rather than decimal"),

        new HistoricEvent(
            1951,
            "LEO",
            "T. Raymond Thompson and  John Simmons develop the first "
                + "business computer, the Lyons Electronic Office (LEO) at "
                + "Lyons Co."),

        new HistoricEvent(
            1951,
            "UNIVAC",
            "UNIVAC I (UNIVersal Automatic Computer I) was introduced--the "
                + "first commercial computer made in the United States and "
                + "designed principally by John Presper Eckert "
                + "& John W. Mauchly"),

        new HistoricEvent(
            1953,
            "The IBM 701 becomes available and a total "
                + "of 19 are sold to the scientific community"),

        new HistoricEvent(
            1954,
            "John Backus & IBM",
            "John Backus & IBM develop the FORTRAN computer programming "
                + "language"),

        new HistoricEvent(
            1955,
            "Bell Labs introduces its first transistor computer."),

        new HistoricEvent(
            1956,
            "Optical fiber was invented by  Basil Hirschowitz, "
                + "C. Wilbur Peters, and Lawrence E. Curtiss"),

        new HistoricEvent(
            1957,
            "Sputnik I and Sputnik II",
            "Sputnik I and Sputnik II are launched by the Russians"),

        new HistoricEvent(
            1958,
            "ARPA (Advanced Research Projects Agency) and NASA is formed"),

        new HistoricEvent(
            1958,
            "Silicon Chip",
            "The first integrated circuit, or silicon chip, is produced "
                + "by the US Jack Kilby & Robert Noyce"),

        new HistoricEvent(
            1959,
            "Paul Baran",
            "Paul Baran theorises on the \"survivability of communication "
                + "systems under nuclear attack\", digital technology and "
                + "symbiosis between humans and machines"),

        new HistoricEvent(
            1960,
            "COBOL",
            "The Common Business-Oriented Language (COBOL) programming "
                + "language is invented."),

        new HistoricEvent(
            1961,
            "Unimate",
            "General Motors puts the first industrial robot, Unimate, "
                + "to work in a New Jersey factory."),

        new HistoricEvent(
            1962,
            "The First Computer Game",
            "The first computer game Spacewar Computer Game invented by "
                + "Steve Russell & MIT"),

        new HistoricEvent(
            1963,
            "The American Standard Code for Information "
                + "Interchange (ASCII) is developed to standardize "
                + "data exchange among computers."),

        new HistoricEvent(
            1963,
            "The Computer Mouse",
            "Douglas Engelbart invents and patents the first computer "
                + "mouse (nicknamed the mouse because the tail came out "
                + "the end)"),

        new HistoricEvent(
            1964,
            "BASIC",
            "John Kemeny and Thomas Kurtz develop Beginner�s All-purpose "
                + "Symbolic Instruction Language (BASIC)"),

        new HistoricEvent(
            1964,
            "Word processor",
            "IBM introduces the first word processor"),

        new HistoricEvent(
            1965,
            "Hypertext",
            "Andries van Dam and Ted Nelson coin the term \"hypertext\""),

        new HistoricEvent(
            1967,
            "Floppy Disk",
            "The floppy disk was invented by David Noble with IBM--Nicknamed "
                + "the \"Floppy\" for its flexibility"),

        new HistoricEvent(
            1969,
            "ARPANET",
            "The U.S. Department of Defense sets up the Advanced Research "
                + "Projects Agency Network (ARPANET ) this network was the "
                + "first building blocks to what the internet is today but "
                + "originally with the intention of creating a "
                + "computer network "
                + "that could withstand any type of disaster"),

        new HistoricEvent(
            1969,
            "Gary Starkweather",
            "Gary Starkweather invents the laser printer whilst working "
                + "with Xerox"),

        new HistoricEvent(
            1969,
            "Seymour Cray",
            "Seymour Cray develops the CDC 7600, the first supercomputer"),

        new HistoricEvent(
            1970,
            "RAM",
            "Intel  introduces the world's first available dynamic RAM "
                + "(random-access memory) chip and the first microprocessor, "
                + "the Intel 4004"),

        new HistoricEvent(
            1971,
            "E-mail",
            "E-mail was invented by  Ray Tomlinson"),

        new HistoricEvent(
            1971,
            "Floppy Disk",
            "IBM introduces floppy disks commercially"),

        new HistoricEvent(
            1971,
            "Liquid Crystal Display (LCD)",
            "Liquid Crystal Display (LCD) was invented by  James Fergason"),

        new HistoricEvent(
            1971,
            "Pocket Calculator",
            "Pocket calculator was invented by Sharp Corporation"),

        new HistoricEvent(
            1972,
            "First Video Game",
            "Atari releases Pong, the first commercial video game"),

        new HistoricEvent(
            1972,
            "The CD",
            "The compact disc is invented in the United States"),

        new HistoricEvent(
            1973,
            "Gateways",
            "Vint Cerf and Bob Kahn develop gateway routing computers to "
                + "negotiate between the various national networks"),

        new HistoricEvent(
            1973,
            "Personal Computer",
            "The minicomputer Xerox Alto (1973) was a landmark step in the "
                + "development of personal computers"),

        new HistoricEvent(
            1973,
            "Robert Metcalfe and David Boggs",
            "Robert Metcalfe creates the Ethernet, a local-area network "
                + "(LAN) protocol"),

        new HistoricEvent(
            1974,
            "SQL",
            "IBM develops SEQUEL (Structured English Query Language) now "
                + "known as SQL"),

        new HistoricEvent(
            1974,
            "WYSIWYG",
            "Charles Simonyi coins the term WYSIWYG (What You See Is What "
                + "You Get) to describe the ability of being able to display "
                + "a file or document exactly how it is going to be printed or "
                + "viewed"),

        new HistoricEvent(
            1975,
            "Microsoft Corporation",
            "The Microsoft Corporation was founded April 4, 1975 by "
                + "Bill Gates and Paul Allen to develop and sell BASIC "
                + "interpreters for the Altair 8800"),

        new HistoricEvent(
            1975,
            "Portable Computers",
            "Altair produces the first portable computer"),

        new HistoricEvent(
            1976,
            "Apple",
            "Apple Computers was founded Steve Wozniak and Steve Jobs"),

        new HistoricEvent(
            1977,
            "Apple Computer�s Apple II, the first "
                + "personal computer with color graphics, is demonstrated"),

        new HistoricEvent(
            1977,
            "MODEM",
            "Ward Christensen writes the programme \"MODEM\" allowing two "
                + "microcomputers to exchange files with each other over a "
                + "phone line"),

        new HistoricEvent(
            1978,
            "Magnetic Tape",
            "The first magnetic tape is developed in the US"),

        new HistoricEvent(
            1979,
            "Over half a million computers are in use in the United States."),

        new HistoricEvent(
            1980,
            "Paul Allen and Bill Gates",
            "IBM hires Paul Allen and Bill Gates to create an operating "
                + "system for a new PC. They buy the rights to a simple "
                + "operating system manufactured by Seattle Computer Products "
                + "and use it as a template to develop DOS."),

        new HistoricEvent(
            1981,
            "Microsoft",
            "MS-DOS Computer Operating System increases its success"),

        new HistoricEvent(
            1982,
            "Commodore 64",
            "The Commodore 64 becomes the best-selling computer of all time."),

        new HistoricEvent(
            1982,
            "SMTP",
            "SMTP (Simple Mail Transfer Protocol) is introduced"),

        new HistoricEvent(
            1982,
            "WordPerfect",
            "WordPerfect Corporation introduces WordPerfect 1.0 a word "
                + "processing program"),

        new HistoricEvent(
            1983,
            "Domain Name System (DNS)",
            "Domain Name System (DNS) pioneered by Jon Postel, "
                + "Paul Mockapetris and Craig Partridge. Seven \"top-level\" "
                + "domain names are initially introduced: "
                + "edu, com, gov, mil, net, org and int."),

        new HistoricEvent(
            1983,
            "More than 10 million computers are in "
                + "use in the United States"),

        new HistoricEvent(
            1983,
            "Windows",
            "Microsoft Windows introduced eliminating the need for a user to "
                + "have to type each command, like MS-DOS, by using a mouse to "
                + "navigate through drop-down menus, tabs and icons"),

        new HistoricEvent(
            1984,
            "Apple Macintosh",
            "Apple introduces the Macintosh with mouse and window interface"),

        new HistoricEvent(
            1984,
            "Cyberspace",
            "William Gibson coins the word cyberspace when he publishes "
                + "Neuromancer"),

        new HistoricEvent(
            1985,
            "Nintendo",
            "The Nintendo Entertainment System makes its debut"),

        new HistoricEvent(
            1985,
            "Paul Brainard",
            "Paul Brainard introduces Pagemaker for the Macintosh creating "
                + "the desktop publishing field"),

        new HistoricEvent(
            1986,
            "More than 30 million computers are in use in the United States"),

        new HistoricEvent(1987, "Microsoft introduces Microsoft Works"),

        new HistoricEvent(1987, "Perl", "Larry Wall introduces Perl 1.0"),

        new HistoricEvent(
            1988,
            "Over 45 million PCs are in use in the United States"),

        new HistoricEvent(
            1990,
            "Microsoft and IBM stop working together to "
                + "develop operating systems"),

        new HistoricEvent(
            1990,
            "The Internet, World Wide Web & Tim Berners-Lee",
            "Tim Berners-Lee and Robert Cailliau propose a \"hypertext\" "
                + "system starting the modern Internet"),

        new HistoricEvent(
            1991,
            "The World Wide Web",
            "The World Wide Web is launched to the public on August 6, 1991"),

        new HistoricEvent(
            1993,
            "At the beginning of the year only 50 "
                + "World Wide Web servers are known to exist"),

        new HistoricEvent(
            1994,
            "The World Wide Web Consortium is founded "
                + "by Tim Berners-Lee to help with the development of common "
                + "protocols for the evolution of the World Wide Web"),

        new HistoricEvent(1994, "YAHOO", "YAHOO is created in April, 1994."),

        new HistoricEvent(
            1995,
            "Amazon",
            "Amazon.com is founded by Jeff Bezos"),

        new HistoricEvent(1995, "EBay", "EBay is founded by Pierre Omidyar"),

        new HistoricEvent(
            1995,
            "Hotmail",
            "Hotmail is started by Jack Smith and Sabeer Bhatia."),

        new HistoricEvent(1995, "Java", "Java is introduced"),

        new HistoricEvent(1996, "WebTV", "WebTV is introduced"),

        new HistoricEvent(
            1997,
            "Altavista introduces its free online translator Babel Fish"),

        new HistoricEvent(1997, "Microsoft acquires Hotmail"),

        new HistoricEvent(
            1998,
            "Google",
            "Google is founded by Sergey Brin "
                + "and Larry Page on September 7, 1998"),

        new HistoricEvent(
            1998,
            "PayPal",
            "PayPal is founded by Peter Thiel and Max Levchin"),

        new HistoricEvent(
            2001,
            "Xbox",
            "Bill Gates introduces the Xbox on January 7th 2001."),

        new HistoricEvent(2002, "Approximately 1 billion PCs been sold"),

        new HistoricEvent(2002, "PayPal is acquired by eBay"),

        new HistoricEvent(2005, "September 12: eBay acquires Skype"),

        new HistoricEvent(
            2006,
            "Skype announces that it has over 100 million registered users.") };
}

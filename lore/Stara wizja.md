Zmierzch bogów chyba już był

1. Szczeliny Rzeczywistości: Upadek Bogów  
2. Szaleństwo Bogów: Kryształowa Wyrocznia  
3. Nieustraszeni Śmiałkowie Ociekającej Epickości  
4.   
5. Strażnik Regulaminu: Zagłada i Wątpliwości  
6. Zawieszenie niewiary: Strach i Nędza  
7. Szczeliny rzeczywistości: Wyrocznia i Odkupienie  
8. Żniwa Cienia: Nieśmiertelne Żywioły  
9. Kryształowa siła: Nauka i Materia  
10.   
11.   
12.   
13. Logika i odkupienie  
14.   
15. Zniszczenie i nieskończoność  
16. Anioły i wątpliwości  
17. Zagłada i obrona  
18. Moc i wyrocznia  
19.   
20. Heban zemsty  
21. Nieśmiertelne Żywioły  
22. Strategia wojny  
23. Wzrost egzekucji  
24. Nauka i Labirynt  
25. Płomień bitwy  
26. Ucieczka potwora  
27. Zabijanie inwazji  
28. Oblężenie oszustwa  
29. Strategia Regulaminu  
30. Dni i zniszczenie  
31. Krew i wojna  
32. Zachwyt  
33. Strefa Zamkowa  
34. Panele boczne

Dołączalni NPC:  
\- Kapłan Saery, huiari podszywający się pod elfa. Religioznawca i historyk, który wie, że legendy o pochodzeniu ras od bogów są prawdziwe i zna wiele tajemnic, legend i ciekawostek zarówno o bogach, jak i ich kultach.  
\- Szaman-Barbarzyńca, duch caluna opętujący zwłoki lentusa. Był barbarzyńcą, który pochodził z plemienia mającego lentusa szamana. Plemię zostało wyrżnięte, ale szaman heroicznie próbował zatrzymać duchy zmarłych w świecie materialnym i zanim zdążył wskrzesić kogoś więcej, sam zginął. Barbarzyńca wszedł w ciało wybawiciela i postanowił podążyć jego śladem zostając szamanem, ale nie miał nauczyciela i idzie mu to różnie.

- Może przebudować rozwój i generalnie umiejętności? Skoro i tak zrezygnowałem z kart, można to zrobić bardziej jak w warcrafcie.

Każda klasa ma poziom. Każdy kolejny poziom kosztuje więcej expa. Za expa można rozwinąć obecną klasę, podnieść statystykę, albo wybrać dodatkową klasę (z automatu dostajesz cantripy i zdolności specjalne \- koszt zależy od tego, ile tego jest)

- Koszt levelu to 200 \* (lvl \- 1\)  
- Koszt nowej klasy to 200 \+ 100 za każdy nowy ficzer, nowa klasa daje cantripy \+ zdolności specjalne  
- Koszt przejścia z zerowego na pierwszy poziom to 200  
- Koszt podniesienia statystyki to 100 za każdy poziom powyżej początkowego \[(następny \- początkowy) \* 100, czyli jeśli postać zaczynała od siły 12, przeskok z 12 na 13 kosztuje 100, a z 16 na 17 kosztuje 500\)

       0\.    **Zdolności specjalne i 2 z 3 cantripów**

1. **2 z 4 umek 1 poziomu**  
2. Wzmocnienie umek   
3. 3 umka 1 poziomu i wzrost HP \+ zasobów  
4. Wzmocnienie kluczowych cech o 1  
5. Wzrost HP \+ zasobów   
6. Wzmocnienie zdolności specjalnych  
7. Wzmocnienie umek  
8. 4 umka 1 poziomu i wzrost HP \+ zasobów  
9. Wzmocnienie kluczowych cech o 1  
10. **2 z 4 umek 2 poziomu**  
11. Wzmocnienie umek  
12. 3 umka 2 poziomu i wzrost HP \+ zasobów  
13. Wzmocnienie kluczowych cech o 1  
14. Wzrost HP \+ zasobów   
15. Wzmocnienie zdolności specjalnych  
16. Wzmocnienie umek  
17. 4 umka 2 poziomu i wzrost HP \+ zasobów  
18. Wzmocnienie kluczowych cech o 1  
19. **2 z 4 umek 3 poziomu**  
20. Wzmocnienie umek   
21. 3 umka 3 poziomu i wzrost HP \+ zasobów  
22. Wzmocnienie kluczowych cech o 1  
23. Wzrost HP \+ zasobów   
24. Wzmocnienie zdolności specjalnych  
25. Wzmocnienie umek  
26. 4 umka 3 poziomu i wzrost HP \+ zasobów  
27. Wzmocnienie kluczowych cech o 1  
28. Wzmocnienie umek, zdolności, HP i zasobów   
29. Wzmocnienie kluczowych cech o 4  
30. Ulti  
-   
-   
-   
-   
-   
-   
- Zrobić listę krokową zaliczającą, ile klas i podklas jest dostępne w drugim kroku z danej podklasy, powtórzyć dla kolejnych ilości kroków. Może policzyć też, w ilu krokach dana podklasa zyskuje dostęp do pełnej gamy klas głównych.  
- Sprawdzanie filtrów w podklasie właściwej i w kolejnych krokach (siła filtra)  
    
- Podklasy będą miały po jednej bojowej umiejętności specjalnej, powtarzalnej. Np. Nekromanta, kapłan Kalnetera itp może mogą animować zwłoki   
* Zaklinacz: Magiczna Zbroja  
*   
- Zmienić część skilli szamana, żeby odnawiał zasoby?  
- Niech rozkazy wojownika zmniejszają koszt?

Wartości oczu, który i włosów liczone na zasadzie:  
eyesColor \= new Color((mom.eyesColor.r \+ dad.eyesColor.r) / 2, (mom.eyesColor.g \+ dad.eyesColor.g) / 2, (mom.eyesColor.b \+ dad.eyesColor.b) / 2\)  
Kolory ubrań i tła losowe (albo nie, ale na pewno niezależne od rodziców);  
eyesColor \= roundToAvailable(eyesColor, eyes\[race\]);

public Color roundToAvailable(Color col, List\<Color\> available) {  
float dif \= 3;  
Color tmp \= available\[0\];  
foreach (Color col2 in available) {  
if (Mathf.Abs(col.r \+ col.g \+ col.b \- col2.r \- col2.g \- col2.b) \< dif) {  
dif \= Mathf.Abs(col.r \+ col.g \+ col.b \- col2.r \- col2.g \- col2.b);  
tmp \= col2;  
}  
}  
return tmp;  
}

Family niech ma pola czytane z pliku:  
Color nestorSkin;  
Color nestorEyes;  
Color nestorHair;  
Color partnerSkin;  
Color partnerEyes;  
Color partnerHair;

**Świat**  
Pomysł i opis generalny:  
Świat na krawędzi, gdzie Pożeracz Dusz nie pozwala przechodzić duszom w zaświaty, przez co nagle nekromancja wiążąca duszę po śmierci przestała być postrzegana jako coś jednoznacznie złego. Siły dobra i światłości uczą się współpracy z rozsądną częścią nieumarłych przeciwko siłom zła wcielonego w postaci Pożeracza Dusz i jego demonów.  
24 bogów reprezentuje 24 godziny doby?

**Do przemyślenia i ogarnięcia kwestia języków\!**

Krasnoludy  
Opis: Rasa, która nade wszystko umiłowała sprawiedliwość. Oczekuje, że świat będzie poukładany, a tych, którzy wprowadzają zamęt, najchętniej widzieliby z dala od siebie. Planują całe swoje życie i są niezwykle sfrustrowani, gdy coś idzie nie tak, ale wierzą, że musi się to dziać z jakiegoś powodu, więc jeśli tylko to możliwe, próbują jeszcze raz. Częstą rozrywką, której się oddają, są rozważania teoretyczne, co w wymyślonej sytuacji przy założonych warunkach byłoby słuszne. Wnioski skrzętnie zapisują w swoich rodowych kodeksach, które gromadzą wiedzę spisywaną przez pokolenia.  
Wygląd: Niscy i krępi. Ich przeciętny wzrost waha się między 120, a 135 centymetrów, przy wadze około 80-100 kilogramów, przy czym mężczyźni zwykle są minimalnie wyżsi i nieco bardziej masywni. Niezależnie od płci przykładają sporą wagę do wyglądu swoich bród i są jedyną rasą, u której kobietom rosną one równie bujnie i intensywnie, co mężczyznom. Głowy mają okrągłe, lub nawet nieco płaskie, uszy i nosy grube i mięsiste, skórę szorstką, zwykle od blado pomarańczowej, przez śniadą do brązowawej. Ich owłosienie jest zwykle dość ciemne, ale waha się od jasnobrązowego i rudego do czarnego, a oczy miewają różne odcienie brązu. Potężne dłonie i silne ręce sprawiają, że naturalnie lepiej radzą sobie z zadaniami siłowymi, niż wymagającymi dokładności. Mogą przeżyć do 300 lat.  
Dostępne klasy: Paladyn (Zilos, Vivera, Anlena), Czempion (Wireus, Merdos), Psion, Wojownik, Mnich, Łowca  
Znani bogowie: Zilos \- 1, Vivera \- 1, Anlena \- 1, Wireus \- 1, Merdos \- 1  
Języki: Wspólny  
Relacje: Dość często miewają wspólne zdanie i stanowisko z ludźmi, sarjakami, mustokami, tulevatami i gnomami, choć ludzie często wydają im się zbyt nieprzewidywalni, sarjaki zaskakująco bezwzględne, a u mustoków irytuje ich to, że nigdy nic nie zapisują. Szczerze nie cierpią za to calunów, którzy wszędzie wprowadzają niepotrzebne zamieszanie, taikan, których uważają za bezmyślnych idiotów i pakan jako leniwych darmozjadów. Są też najczęściej wrogo nastawieni do diebenów i iltów, których uważają za równie zdemoralizowanych i złych, co demony, czy potwory.

Ludzie  
Opis: Rasa uwielbiająca opowieści, każdy problem są w stanie przedstawić przy ich pomocy. Mają silną potrzebę naprawiania tego, co uznają za własne błędy i nigdy się nie poddają, zawsze zaczynają od nowa, na przekór wszystkiemu. Dość mocno polegają na sobie nawzajem i mają zwykle silną potrzebę założenia rodziny i rozmnożenia się, co może być powiązane z ich dość krótkim życiem. Potrafią wykazywać tendencje zarówno do porządku, jak i impulsywnych reakcji, ale zwykle przynajmniej część ich duszy tęskni za świętym spokojem.  
Wygląd: Ich wzrost potrafi się wahać od 150 do 190 centymetrów, a waga od 50 do 120 kilogramów. Mężczyźni najczęściej są wyżsi i masywniej zbudowani od kobiet. Ich skóra miewa najróżniejsze odcienie od bardzo jasnej do ciemnobrązowej, ale najczęściej nie wychodzi poza mieszanki i wariacje różu, pomarańczy, brązu i miedzi o różnej jasności i nasyceniu. Ich głowy miewają zaskakująco różne kształty od okrągłych i kwadratowych, przez trójkątne do pociągłych. Nosy i uszy występują u nich również w tylu różnych wariantach, że ciężko powiedzieć o nich coś charakterystycznego poza tym, że lubią ozdabiać je kolczykami, a uszy mają zaokrąglone. Ich owłosienie zwykle ma jakiś odcień blondu, brązu, rudego, czy czerni, a oczy reprezentują warianty niebieskiego, zielonego, brązowego i ich mieszanek. Na starość włosy im blakną i siwieją. Mogą dożyć do 80 lat, ale sporadycznie zdarza im się żyć dłużej, choć często wymagają wtedy opieki i pomocy.  
Dostępne klasy: Kapłan (Vivera, Zilos, Saera, Custus), Sługa Boży (Merena), Paladyn (Vivera, Zilos, Saera, Custus), Czempion (Merena), Czarnoksiężnik, Czarodziej, Szaman, Druid, Wojownik, Barbarzyńca, Bard, Łotrzyk, Skrytobójca, Mnich, Łowca, Zwiadowca  
Znani bogowie: Vivera \- 1, Zilos \- 1, Saera \- 1, Custus \- 1, Merena \- 1  
Języki: Wspólny  
Relacje: Ze względu na ich wysokie zróżnicowanie pod względem charakteru różnie dogadują się z przedstawicielami innych ras, ale najczęściej znajdują przysłowiowy wspólny język z krasnoludami, haituvami, valotami, elfami, mustokami i lentusami. Mimo tego większość z nich uważa krasnoludy za trochę sztywne i uparte, haituvi bywają dla nich frustrujące ze względu na brak wyobraźni, przy kontaktach z mustokami ich wrodzona ciekawość bywa wystawiana na próbę, a spokój lentusów w kontekście długości ich życia często wprawia ludzi w melancholię i myślenie o śmierci. Większość z nich nienawidzi diebenów, sortaji i liskotów, uważając ich za typowych złoczyńców z opowieści, a iltami gardzą ze względu na ich moralną oślizgłość i tchórzostwo.

Elfy  
Opis: Rasa, która uwielbia kierować się uczuciami i emocjami. Im silniejsze te uczucia i emocje są, tym lepiej, a czy coś może budzić większe emocje, niż pomoc bezbronnym wbrew panującemu prawu? Ich relacje opierają się na silnych namiętnościach, choć nie są powierzchowne. Potrafią błyskawicznie nawiązać bardzo głęboką więź trwającą nieraz do samej śmierci. Uwielbiają sztukę i szybko zapalają się do pomysłów, ale zwykle brak im wytrwałości, żeby doprowadzić je do końca, jeśli wymagają dłuższej pracy. Kochają jaskrawe kolory i zabawę. Większość z nich wierzy, że dobroć zawsze zostanie wynagrodzona, a ze snów można odczytać głębszą prawdę.  
Wygląd: Ich wzrost waha się między 160, a 175 centymetrów, a waga w granicach od 50 do 70 kilogramów, przy czym mężczyźni i kobiety nie różnią się pod tym względem. Ich skóra miewa zaskakująco różne kolory, od delikatnej pomarańczy, przez żółcie, zielenie, brązy i błękity do fioletów, a jej jasność i nasycenie waha się od intensywnych do niemal pozbawionych barwy i od bardzo jasnych nawet do grafitowej czerni. Włosy mają wyjątkowo proste, zwykle bardzo długie, a ich kolor zwykle nie odbiega zbytnio od koloru skóry, choć jest nieco ciemniejszy. Głowy są smukłe i podłużne, zwężające się nieco ku dołowi, na ciele nie rośnie im owłosienie, oczy są w kształcie migdałów, najczęściej niebieskie, srebrne, lub złote, uszy długie, smukłe i spiczaste, a nosy wąskie. Mogą dożyć 900 lat, choć najczęściej umierają wcześniej, robiąc coś ryzykownego.  
Dostępne klasy: Kapłan (Saera, Vivera, Eheia), Sługa Boży (Ither), Czarownik, Czarnoksiężnik, Psion, Druid, Barbarzyńca, Bard, Łotrzyk, Skrytobójca, Łowca, Zwiadowca  
Znani bogowie: Saera \- 1, Vivera \- 1, Eheia \- 1, Ither \- 1, Inera \- 1  
Języki: Wspólny  
Relacje: Większość z nich najlepiej dogaduje się z litorami i taikanami, ale żyją również w dobrych stosunkach z ludźmi, valotami, haituvami, calunami i pakanami, chociaż ludzie, valoci i haituvi wydają im się trochę nudni i przewidywalni, a caluni i pakani niemoralni. Kompletnie nie potrafią znaleźć porozumienia z sarjakami, mustokami, tulevatami i gnomami, jako rasami zbyt poukładanymi i przykładającymi zbyt dużą wagę do porządku. Ponadto jeśli wiedzą, że może im to ujść płazem, na sortaje, liskotów, diebenów i iltów reagują bezwzględną agresją i próbą zabicia na miejscu.

Sarjaki  
Opis: Rasa, której przedstawiciele najczęściej nie rozumieją konceptu emocji, a dobro i zło postrzegają przez pryzmat tego, co jest zgodne z prawem, a co nie. Prawo oznacza porządek i dzięki prawu świat może być poukładany, a powinien być poukładany, bo inaczej jaki w nim sens? W niepoukładanym świecie może dojść do czegoś niespodziewanego. Robią uczciwie to, do czego się zobowiązują, a na tych, którzy z obojętnie jakiego powodu tego nie robią, patrzą z niechęcią i wyższością.  
Wygląd: Mierzą około 170 do 180 centymetrów, a ważą zwykle między 80, a 115 kilogramów, przy czym mężczyźni są zwykle niżsi i lżejsi od kobiet. Ich głowy są względnie okrągłe i ogólnie nieco mniejsze, niż wskazywałyby klasyczne proporcje ciała. Uszy mają duże, przypominające trochę płetwy, odstające na boki i sterczące w górę, ale dość miękkie i giętkie, dzięki czemu falują nieco przy poruszaniu. Nosy mocno zadarte, a usta bardzo szerokie, niemal od ucha do ucha. Ich oczy są zaskakująco duże i wyłupiaste, a zamiast klasycznych powiek mają wyłącznie błony migawkowe, co sprawia, że budzą niepokój u większości osób spoza swojej rasy i sprawiają wrażenie wiecznie obserwujących, nawet we śnie. Ich tęczówki mają zwykle barwę bardzo jasnego fioletu. Skórę mają chropowatą, w kolorach na spektrum fioletu, od jasnego do dość ciemnego, z zabarwieniem skłaniającym się u niektórych w stronę czerwieni, a u innych w stronę niebieskiego. Naturalnie nawoskowane włosy rosną im wyłącznie na głowie i nad oczami w postaci gigantycznych brwi, a ich kolor waha się między ciemnym, a bardzo ciemnym fioletem. Mają nieco przykrótki tułów i długie, zwykle potężnie umięśnione ręce i nogi. Mogą dożyć 120 lat.  
Dostępne klasy: Kapłan (Anlena, Zilos, Custus, Deros), Czarodziej, Psion, Szaman, Wojownik, Mnich  
Znani bogowie: Anlena \- 1, Zilos \- 1, Custus \- 1, Deros \- 1, Nuvva \- 1  
Języki: Wspólny  
Relacje: Bardzo dobrze dogadują się z mustokami, tulevatami i gnomami, chociaż uważają gnomy za nieco amoralne przez wzgląd na ich tendencje do szukania luk w zasadach, ale dość łatwo znaleźć im wspólny język również z krasnoludami, których podejścia do moralności nie rozumieją, z sortajami, do których podchodzą z pewną rezerwą, bo dziwnie wiele rzeczy idzie po ich myśli, a także z lentusami, których obojętność wobec reguł trochę ich irytuje. Nie cierpią calunów, taikanów i elfów ze względu na ich absolutny brak poszanowania dla zasad i reguł. Nie przepadają też za pakanami, których ostentacyjnie olewający stosunek do czegokolwiek budzi w nich obrzydzenie i za litorami, którzy najpierw nie szanują reguł, a potem wydaje im się, że jeden skok do wody zmaże ich winę.

Lentusi  
Opis: Bardzo długowieczna rasa, która w sposób stały i konsekwentny potrafi dążyć latami, dekadami, a nawet stuleciami do obranego celu. Nade wszystko umiłowali spokój i muzykę, ale ich muzyka zazwyczaj prędzej czy później usypia przedstawicieli innych ras. Tworzą długoterminowe plany, ale nie przejmują się, jeśli coś idzie niezgodnie z nimi, bo wiedzą, że będą mogli spróbować jeszcze raz, albo przetestować inne podejście. Mają czas i w pełni z tego korzystają.  
Wygląd: Są najwyższą humanoidalną rasą, mierzą od 250 do 300 centymetrów, a ważą najczęściej od 320 do 500 kilogramów, przy czym większość tej wagi stanowi potężnie zbudowany, niezwykle twardy i wytrzymały szkielet. Ich kości są materiałem wyjątkowo cennym i niemal niedostępnym ze względu na ich długość życia i niezbyt dużą ilość. Mężczyźni i kobiety nie różnią się pod względem wzrostu i wagi. Głowy mają podłużne, na czubku zaokrąglone i mocno pomarszczone, ale ich brody tworzą dwie wypustki. Zewnętrzne kąciki ich bardzo ciemnozielonych oczu opadają silnie ku dołowi, a powieki są grube i ciężkie, przez co zawsze wyglądają na zmęczonych, albo sennych. Zamiast nosa mają dwie proste szpary do oddychania, a ich uszy są wyjątkowo drobne. Górna, mocno pofalowana warga, całkowicie zakrywa dolną. Mają zdecydowany nadmiar wiecznie pomarszczonej skóry w kolorze ciemnozgniłej zieleni, albo brązu, czy czegoś pomiędzy. Owłosienia brak. Ze względu na to, że są bardzo wysocy, sprawiają wrażenie dość zabawnie długich, cienkich i wyciągniętych, ale są stabilniejsi, niż ktokolwiek inny. Najstarsi mają prawdopodobnie około 9000 lat, ale po tak długim czasie ciężko odróżnić jedno stulecie od drugiego i nie są tego pewni.  
Dostępne klasy: Czarownik, Czarodziej, Psion, Szaman, Druid, Wojownik, Bard, Mnich, Łowca  
Znani bogowie: Custus \- 1, Anlena \- 1, Vivera \- 1, Eheia \- 1, Oara \- 1  
Języki: Wspólny  
Relacje: Ze sporą trudnością przychodzi im dogadywanie się z innymi rasami, ale potrafią dojść do porozumienia z ludźmi, którzy na ogół potrafią być w miarę rozsądni, mimo ich bardzo krótkiego życia i zwierzęcego pędu do rozmnażania. Umieją dogadać się również z sarjakami, choć ich bardzo sztywne przywiązanie do zasad bywa wielką zawadą, a także z diebenami, którzy co prawda dopuszczają się nieraz rzeczy uznawanych przez lentusów za nieładne, ale zwykle potrafią być racjonalni. Nie są wrogo nastawieni do kogokolwiek, poza rasami uznanymi powszechnie za potwory, ale też kompletnie nie potrafią znaleźć wspólnego języka z większością innych istot.

Caluni  
Opis: Dość krótkowieczna rasa, której przedstawiciele zazwyczaj mają okres utrzymania uwagi jak u małego dziecka. Są wulkanami pomysłów, które natychmiast zaczynają realizować, porzucając wcześniej poprzedni, jeśli zachodzi taka potrzeba, a zachodzi zazwyczaj. Kochają taniec, zabawę i psikusy. Potrafią zarówno tworzyć wspaniałe rzeczy, jak i być czystą siłą destrukcji, czasem robiąc obie te rzeczy jednocześnie. Wierzą, że ich sny są równie prawdziwe, co rzeczywistość i czasem nie odróżniają, co im się przyśniło, a czego doświadczyli naprawdę. Ich relacje są pełne bardzo silnych emocji, a związki burzliwe i intensywne, ale zarówno w jednych, jak i drugich, najczęściej brak głębszych uczuć.  
Wygląd: Bardzo drobna rasa, ich wzrost waha się między  70, a 90 centymetrów, a przeciętna waga to zaledwie od 10 do 19 kilogramów, przy czym kobiety są odrobinę niższe od mężczyzn, ale nie różnią się od nich wagą. mają dość trójkątne głowy z uszami na czubku, zamiast po bokach, przez co przypominają trochę konie, albo koty. Oczy wielkie i z silnie wyciągniętymi w górę zewnętrznymi kącikami, które mogą przybrać dowolny jaskrawy kolor i mają źrenice w kształcie gwiazdy czteroramiennej. Ich nosy są drobne i wąskie, usta cienkie i dość szerokie, a skóra śliska, i w kolorach od jaskrawo żółtego do płomiennie pomarańczowego. Włosy mają bujne, rude, albo brązowe, a mężczyznom rosną długie, wąskie bródki przywodzące na myśl kozy. Żyją zaledwie do 30 lat, ale bardzo szybko osiągają dojrzałość płciową, bo dojrzałości emocjonalnej według niektórych nie osiągają nigdy.  
Dostępne klasy: Czarownik, Czarnoksiężnik, Szaman, Druid, Barbarzyńca, Bard, Łotrzyk, Skrytobójca, Łowca  
Znani bogowie: Eheia \- 1, Saera \- 1, Ahana \- 1, Inera \- 1, Kena \- 1  
Języki: Wspólny  
Relacje: Doskonale dogadują się z taikanami, ale dobrze im się układa również z elfami, których idealizm czasem ich męczy i irytuje, z pakanami i iltami, którzy jednak są zdaniem calunów bardzo leniwi i bywają nudni, oraz są jedną z niewielu ras, która nie reaguje na roznoszące choroby tartuny natychmiastową agresją, a zaciekawieniem. Serdecznie nienawidzą sarjaków, krasnoludów, tulevatów, sortaji i liskotów, których zamiłowania do zasad i reguł nie tylko nie rozumieją, ale jest w ich rozumieniu złe i okrutne. Absolutnie nie potrafią się dogadać z mustokami i lentusami, którzy są dla nich zbyt spokojni i racjonalni, ale dogadywanie się z gnomami, choć przysparza wielu trudności, bywa z ich perspektywy niezwykle opłacalne, ponieważ gnomy dobrze dogadują się z wieloma rasami, które zupelnie nie posłuchałyby caluna.

Sortaje  
Opis: Rasa, która do perfekcji opanowała wykorzystywanie ustanowionych praw do własnych celów i dla własnego zysku. Uważają się za lepszych od innych, więc zwykle nie widzą nic złego w wyzyskiwaniu słabszych, o ile wiedzą, że nie powinno się to spotkać z konsekwencjami, a potrafią tak wszystko obmyślić i zaplanować, żeby sobie to zapewnić. Najczęściej uważają, że ból i cierpienie, cudze lub własne, to mała cena, jeśli na szali znajdzie się władza, czy potęga. Potrafią nawet czerpać z nich przyjemność, bo traktują je jako objawy nadchodzącego triumfu.  
Wygląd: Mierzą od 175 do 200 centymetrów i najczęściej ważą między 75, a 100 kilogramów, przy czym mężczyźni są nieco niżsi od kobiet i zwykle znacznie słabiej od nich zbudowani. Głowy mają dość wyciągnięte i smukłe, o bardzo wyrazistych i mocnych rysach, potężnych kościach policzkowych i szerokich podbródkach. Na ciele nie rośnie im zarost, a włosy mają czarne jak smoła. Oczy są w kształcie migdałów, najczęściej krwiście czerwone, czasem lodowato niebieskie. Ich uszy sterczą trochę na boki, są spiczaste, dość szerokie i ostrymi czubkami skierowane w górę, a nosy są wąskie. Usta przypominają kreskę, a cienkie wargi są purpurowe, co podobnie jak włosy bardzo mocno kontrastuje z idealnie białą i gładką, ale matową skórą. Mogą dożyć 200 lat.  
Dostępne klasy: Kapłan (Deros, Anlena, Oara), Paladyn (Deros, Anlena, Oara), Czarodziej, Psion, Wojownik, Mnich, Zwiadowca  
Znani bogowie: Deros \- 1, Anlena \- 1, Oara \- 1, Gisstus \- 1, Yreus \- 1  
Języki: Wspólny  
Relacje: Najlepiej dogadują się z liskotami, bo pomijając tendencję tych drugich do podporządkowania się smokom, ich podejście do świata i życia jest bardzo przybliżone. Dość dobrze rozumieją się też z sarjakami i gnomami, choć tych drugich uważają za niebezpiecznie sprytnych i traktują z pewną rezerwą. Należą do ras dość chętnie wchodzących w porozumienie z rozumnymi nieumarłymi, a jeszcze chętniej korzystają ze służby tych pozbawionych świadomości. Często tworzą spójny front z diebenami, choć uważają, że brak im taktu i zupełnie niepotrzebnie łamią nieraz ustanowione prawa. Żywią jednak lodowate uczucia wobec calunów, taikanów i litorów, jako zbyt nieprzewidywalnych, żeby choćby próbować się z nimi porozumieć, a także wobec haituv, którzy kategorycznie trzymają swoją wielką wiedzę poza ich zasięgiem. Szczerze nienawidzą elfów, przez wzgląd na ich chorą moralność i agresję wobec ich rasy, a także valotów, którzy jako jedyna rasa aktywnie zwalczają bardzo przydatnych nieumarłych. Często nie są pewni, jak podchodzić do pakan, ponieważ na ogół po prostu gardzą ich brakiem ambicji, ale gdy zaczynają działać, stają się równie wkurzający i niebezpieczni, co caluni, czy taikani, albo nawet elfy.

Diebeny  
Opis: Rasa, dla której nie ma większej świętości, niż własna wygoda, a często, żeby sobie ją zapewnić, konieczne są pieniądze, albo mniejsze, lub większe kłamstwo. To indywidualiści, którzy nawet innych przedstawicieli swojego ludu uważają za gorszych od siebie i są skłonni wykorzystać. Lubią zmieniać otaczający ich świat i przekształcać go tak, jak im wygodnie. Z tego powodu jeśli tylko mogą skorzystać z portalu, zamiast podróżować w klasyczny sposób, zawsze to zrobią. To dekadenci pragnący zaspokoić swoje wybujałe potrzeby, zanim dopadnie ich śmierć i rozpłyną się w nicość.  
Wygląd: Ich wzrost waha się między 150, a 180 centymetrów, a przeciętna waga od zaledwie 60 do aż 110 kilogramów. Mężczyźni są zwykle trochę niżsi od kobiet, ale za to ciężsi. Głowy mają w kształcie jajka, ale z dużymi kośćmi policzkowymi, na których mają po dwie kościane wypustki. Nisko osadzone usta, niczym nie wyróżniające się uszy, nosy płaskie i szerokie, a oczy duże, pałające pogardą i jaskrawo zielone. Ich porowata skóra przybiera kolory od jadowitej zieleni do cyjanu. Na ciele i twarzy nie rośnie im owłosienie, a włosy na głowie przybierają różne odcienie ciemnej zieleni. Po obu stronach podbródka rosną im dość długie, bezwładne i silnie unerwione macki ostrzegające przed potencjalnym ostrzem zbliżającym się do szyi na ułamki sekundy wcześniej. Mogą dożyć 120 lat.  
Dostępne klasy: Paladyn (Oara, Deros), Czarownik, Czarnoksiężnik, Czarodziej, Wojownik, Barbarzyńca, Łotrzyk, Skrytobójca, Łowca, Zwiadowca  
Znani bogowie: Oara \- 1, Deros \- 1, Carisa \- 1, Ahana \- 1, Eesir \- 1  
Języki: Wspólny  
Relacje: Zwykle nie należą do istot, którym zależy na dogadywaniu się z kimkolwiek, ale większość z nich ma poprawne stosunki z sortajami, choć unikają konieczności wchodzenia z nimi w porozumienia ze względu na ich nieżyciowe podejście do zasad. Najłatwiej im współpracować z rozumnymi nieumarłymi, choć potrafią też znaleźć wspólny język ze zbyt wycofanymi i skrytymi iltami oraz lentusami, choć przed tymi ostatnimi większość swoich spraw i motywacji starają się trzymać w sekrecie. W przeciwieństwie do większości cywilizowanych ras nie reagują agresją na bezwzględnych huiari, roznoszące zarazy tartuny i kanibalistycznych wendigo, a nawet zdarza im się wchodzić z nimi w układy, choć ich łamanie przychodzi im równie łatwo, co nawiązywanie. Bezrozumnych nieumarłych traktują utylitarnie. Najbardziej na świecie nienawidzą elfów i litorów, których starają się metodycznie eliminować, na ile jest to możliwe i okoliczności pozwolą. Nie cierpią też krasnoludów, haituv, ludzi i valotów, którzy bardzo często stają im na drodze do celu, a szczególnie valotów, którzy zaciekle niszczą całkiem przydatnych nieumarłych.

Gnomy  
Opis: Niezwykle pomysłowa i sprytna rasa, przykładająca dużą wagę do rodziny i lokalnych społeczności. Zwykle są bardzo pracowici, a wielu z nich wybiera zawody związane z rzemiosłem i handlem. Z zasady przestrzegają prawa, ale traktują je utylitarnie, jako jedno z narzędzi, więc jeśli im na czymś zależy, potrafią to tak zaplanować, żeby prawa nie złamać, a jedynie je obejść, lub lekko nagiąć. Mimo tego zgadzają się, że jest potrzebne, żeby nikt uczciwemu gnomowi pracy nie utrudniał. Podchodzą z pewną rezerwą do nie-gnomów i potrafią patrzeć na nich z wyższością, ale w ich mniemaniu gnomem nie trzeba się urodzić, o ile jakaś gnomia społeczność zechce cię uznać za honorowego gnoma.  
Wygląd: Mierzą od 90 do 105 centymetrów, a ważą zwykle od 18 do 25 kilogramów. Kobiety i mężczyźni nie różnią się specjalnie pod względem rozmiaru i budowy. Głowy mają dość okrągłe, trochę węższe poniżej kości policzkowych. Uszy odstające i raczej duże, a nosy spore i dość wąskie, ale często garbate, albo o czubku przywodzącym na myśl małego ziemniaka. Oczy mają dość spore i najczęściej od zielonych, przez brązowe do złotawych. Ich skóra jest w większości miejsc pokryta kręconymi kudłami, choć tylko na głowie i twarzy rosną one bardzo gęsto, choć na twarzy tylko u mężczyzn. Sama skóra najczęściej ma kolor na spektrum od kremowego do jasnego brązu, a owłosienie bywa czarne, brązowe, rude, albo przybiera barwę pomiędzy. Mają dość smukłe i wyjątkowo zręczne dłonie, a kończyny raczej niezbyt umięśnione. Mogą dożyć 250 lat.  
Dostępne klasy: Kapłan (Anlena, Zilos, Deros), Sługa Boży (Nuvva, Merdos), Czarodziej, Bard, Łotrzyk  
Znani bogowie: Nuvva \- 1, Anlena \- 1, Zilos \- 1, Deros \- 1, Merdos \- 1  
Języki: Wspólny  
Relacje: Bardzo często mają wspólne wspólne zdanie, stanowisko i interesy z krasnoludami, sarjakami i tulevatami, choć krasnoludy i sarjaki wydają im się trochę zbyt kategoryczni w kwestiach zakazów i nakazów. Potrafią też dogadywać się z mustokami, chociaż przez wzgląd na ich silnie rodzinny charakter widzą w nich pewnego rodzaju konkurencję dla swoich społeczności, a także z sortajami, chociaż uważają ich za dość paskudne osoby i nie robią tego bardzo chętnie. Gardzą większością pakan, którzy według nich reprezentują sobą same wady z lenistwem na czele. Bardzo chłodnymi uczuciami darzą elfy i litory, ze względu na wybierane przez nich nieżyciowe zawody i nieprzewidywalny charaktem, ale szczerze nie cierpią calunów i taikan, ponieważ widzą w nich spotęgowane odbicie swoich tendencji do naginania i omijania prawa bokiem, a jak wiadomo najbardziej denerwują nas w innych własne cechy, z których nie jesteśmy specjalnie dumni.

Haituvi  
Opis: Delikatna rasa, której nauka magii przychodzi tak łatwo, jak innym nauka posługiwania się widelcem. Kochają tworzyć piękne i dobre rzeczy, gromadzić tajemnice, zdobywać wiedzę i poznawać opowieści, choć im samym zwykle brakuje wyobraźni, aby tworzyć własne. Wszystko to spisują, aby wiedza jednego pokolenia nie przepadła wraz z jego wyginięciem, ale jednocześnie chronią swojej wiedzy przed tymi, którzy mogliby wykorzystać ją w niegodziwym celu. Wielopokoleniowe skupienie na tych sprawach doprowadziło do atrofii niektórych partii mięśni. Gdy brakuje im pożywienia, potrafią czerpać energię prosto ze słońca.  
Wygląd: Ich wzrost waha się między 220, a 240 centymetrów, ale waga zwykle sięga zaledwie od 78 do 98 kilogramów. Mężczyźni i kobiety nie różnią się specjalnie pod względem wzrostu i budowy. Głowy mają bardzo długie i wąskie, podbródki trójkątne, nosy bardzo drobne i podłużne, usta szerokie i cienkie, a oczy wielkie, pozbawione źrenic i świecące lekkim, białym światłem, które powieka jest w stanie całkowicie zatrzymać. Jednak nawet z zamkniętymi oczami mają dość dobrą orientację w sytuacji, ponieważ nie mają klasycznych uszu, a sterczące na boki czułki odpowiedzialne za zmysł słuchu i wspierające wzrok. Ich skóra jest bardzo cienka i może mieć różne odcienie błękitu, a włosy rosną im tylko na głowie, są słabe i białe, lub srebrne. Dłonie mają bardzo delikatne, długie i cienkie, ale dość zręczne, a palce posiadają dodatkowy staw. Mogą dożyć 1000 lat.  
Dostępne klasy: Kapłan (Vivera, Saera), Sługa Boży (Merena, Wireus, Ither), Czarownik, Czarnoksiężnik, Czarodziej, Psion, Szaman, Bard, Łotrzyk  
Znani bogowie: Merena \- 1, Wireus \- 1, Vivera \- 1, Ither \- 1, Saera \- 1  
Języki: Wspólny  
Relacje: Bardzo dobrze dogadują się z ludźmi i valotami, przy czym bardzo zazdroszczą ludziom naturalnej umiejętności tworzenia opowieści, co ma też efekt uboczny w postaci dozy niezbyt przychylnych uczuć, jakie często żywi się do kogoś lepszego w czymś, na czym nam zależy. Potrafią dogadać się również z elfami, litorami i mustokami, choć elfy i litory są ich zdaniem niezrównoważeni i należy uważać przy kontaktach z nimi. Bardzo podziwiają za to mustoki ze względu na ich zdolność zapamiętywania wszystkiego bez zapisywania, choć niezbyt podoba im się, że nie chcą się tą wiedzą dzielić nawet w słusznej sprawie. Uważają sortaje, diebeny, ilty i liskotów za zwyczajnie złych i niebezpiecznych, przez co poza istotami uznanymi powszechnie za potwory, swojej wiedzy chronią przede wszystkim przed nimi. Niezbyt chętnie dzielą się nią również z pozostałymi rasami, z którymi nie utrzymują dobrych stosunków, ale przy dobrej argumentacji i sensownych powodach są skłonni udzielić dostępu do swoich bibliotek.

Mustoki  
Opis: Rasa istot charakteryzujących się pamięcią idealną, a co ciekawe często dziedziczoną bez konieczności faktycznego jej przekazywania. Tajemnicą jest, jak to w ogóle możliwe i dlaczego nie zawsze tak się dzieje. Jeśli sami to wiedzą, nigdy z nikim się tą wiedzą nie podzielili. W ich kulturze jednym z podstawowych pojęć jest kolektywny umysł. O wiele bardziej skupiają się na tym, żeby jak największa ilość wiedzy była zmagazynowana w ich społeczności, niż na tym, żeby posiadać ją osobiście. Spora część z nich uważa nawet, że świadomość istnienia jakiejś wiedzy, ale nie poznanie jej, dodaje życiu pikanterii. Wielu z nich poświęca życie na medytację, inni zaś skupiają się na gromadzeniu wiedzy, lub poznawaniu magii, przy czym najczęściej liczy się dla nich samo poznanie, nie korzystanie z niej. Lubią posługiwać się metaforami, przypowieściami i alegoriami, przez co dla wielu mogą wydać się bardzo tajemniczy.  
Wygląd: Mierzą od 210 do 230 centymetrów, a ważą od 100 do 130 kilogramów, przy czym kobiety są minimalnie wyższe i trochę lepiej zbudowane od mężczyzn. Głowy mają pociągłe, ale twarze nie tak wąskie, jak można by się spodziewać. Na ich szczycie sterczą spore uszy o mocno zarośniętym wnętrzu. Nosy mają raczej wąskie, oczy zielone, brązowe, niebieskie, albo piwne, a nad oczami potężne brwi, które muszą kręcić i podwijać na boki, albo przycinać, choć zwykle wybierają pierwszą opcję, bo ich zdaniem sprawia, że wyglądają mądrzej. Skórę mają zwykle śniadą i miejscami mocno zarośniętą, chociaż czasem zdarza się bledsza, albo bliższa jasnemu brązowi. Mężczyźni mają bardzo bujny zarost, który jednak nie rośnie im pod nosem. Całe owłosienie może mieć różne odcienie brązu. Mogą dożyć 1400 lat.  
Dostępne klasy: Kapłan (Zilos, Vivera), Sługa Boży (Wireus, Merena, Merdos), Czarownik, Czarodziej, Psion, Szaman, Bard, Mnich  
Znani bogowie: Wireus \- 1, Zilos \- 1, Merena \- 1, Vivera \- 1, Merdos \- 1, do 6 losowych innych (żeby odwzorować ich pęd do wiedzy i jej dziedziczenie)  
Języki: Wspólny  
Relacje: Bardzo dobrze dogadują się z sarjakami, tulevatami i krasnoludami, chociaż u tych ostatnich przeszkadza im trochę zapisywanie wszystkiego, podobnie jak u haituv, z którymi mimo to również dość łatwo wchodzą w porozumienia. Z ludźmi i gnomami kontakty mają poprawne, choć irytuje ich, jak często obie te rasy zwracają się do nich po jakąś wiedzę potrzebną w “bardzo istotnej sprawie”, powołując się na kwestie życia i śmierci, czy inne bzdury. Ubolewają, że inne rasy zupełnie niepotrzebnie skupiają się na praktycznym wykorzystaniu wiedzy i nie liczy się dla nich wiedza sama z siebie. Bardzo nie lubią mieć styczności z calunami, taikanami i pakanami, którzy ich zdaniem okazują całkowity brak zainteresowania i szacunku wobec wiedzy i mądrości, a elfy i litory zwyczajnie ich denerwują przez wzgląd na to, że często zaczynają działać znając strzępek informacji, kompletnie ignorując szerszy obraz.

Pakani  
Opis: Rasa przekonana, że jeśli komuś pisane jest osiągnąć coś wielkiego, przyjdzie to do niego samo, a jeśli nie przyjdzie, to niezależnie, ile by zrobił, i tak nic z tego nie będzie, więc nie warto się starać. Skupiają się zamiast tego na doczesności i uciechach cielesnych. Niektórzy preferują niespieszne orgie, inni romantyczne wieczory we dwoje, za to wszyscy uwielbiają półmrok i delikatne światło świec, odurzanie się różnymi substancjami i odpoczynek przy muzyce, ale potrafią też okazać inicjatywę, a wręcz żarliwy zapał, jeśli poczują, że dotyka ich przeznaczenie, albo coś zaczyna zagrażać ich stylowi życia, którego będą bronić do ostatniej kropli krwi. Chociaż jeśli będą mieli wybór, zdecydują się raczej na zasadzkę, niż otwartą konfrontację.  
Wygląd: Ich wzrost waha się między 150, a 160 centymetrów, a ważą najczęściej od 55 do 90 kilogramów, choć pewnie zdrowsi byliby utrzymując wagę w granicach od 44 do 70 kilogramów, ale przez wzgląd na charakter większość z nich ma z tym problem. Mężczyźni nie różnią się od kobiet pod względem wzrostu i wagi. Głowy mają w kształcie, który przypominałby prostokąt, gdyby ich twarze nie były dość nalane. Oczy spore, ale przez ciężkie powieki nie sprawiają takiego wrażenia. Ich kolor bywa niebieski, albo pomarańczowy. Skórę mają zwykle kremową, a włosy blond, lub w odcieniach brązu, podobnie jak występujące u mężczyzn wąsiki, które zakręcają sobie w górę. Uszy mają po bokach głowy, ale przy skroniach, przy czym są one spore i trójkątne. Mogą nimi poruszać, jak niektóre zwierzęta, ale robią to najczęściej odruchowo. Nosy mają lekko pulchne, a usta pełne i dość szerokie. Mogą dożyć 60 lat.  
Dostępne klasy: Czarnoksiężnik, Szaman, Druid, Bard, Łotrzyk, Skrytobójca, Łowca  
Znani bogowie: Kena \- 1, Eheia \- 1, Inera \- 1, Viona \- 1, Saera \- 1  
Języki: Wspólny  
Relacje: Dość dobrze dogadują się z elfami, calunami, taikanami i iltami, chociaż elfy wydają im się przesadnie ekspresyjne, caluni zbyt energiczni i męczący, z taikanami często wiodą spory o naturę przeznaczenia, a ilty uważają za dość nieprzyjemne osobniki. Kompletnie nie rozumieją rozsądnych i racjonalnych ras, które zwykle trzymają się jakiś zasad, przez co zupełnie nie potrafią się dogadać z krasnoludami, sarjakami, mustokami, tulevatami, gnomami, sortajami i liskotami.

Valoci  
Opis: Rasa ponad wszystko kochająca słońce, do którego niezachwiany dostęp jest konieczny do ich rozmnażania. Typowi przedstawiciele tej rasy są prostoduszni i potrafią cieszyć się z małych rzeczy. Najchętniej mieszkają w przestronnych, otwartych domach w okolicy akwenów wodnych, gdzie zanurzają się kilka razy dziennie, ponieważ czyste ciało prowadzi do czystego serca i czystego umysłu. W ich domach zawsze jest jasno. Jeśli akurat nie wpada do nich światło słoneczne, jest tam przynajmniej kilka innych źródeł światła. Sami też potrafią przez dość ograniczony czas świecić jasnym blaskiem. Chronią życia, czczą je, a akty poczęcia i narodzin są dla nich przeżyciami niemal mistycznymi.  
Wygląd: Mierzą od 165 do 180 centymetrów, a ważą zwykle w granicach od 75 do 85 kilogramów. Kobiety są minimalnie wyższe, ale też lżejsze od mężczyzn. Głowy mają eliptyczne, przy czym kości policzkowe bardzo mocno zarysowane, szczęki dość wąskie, a boki żuchwy bardzo cofnięte, przez co sprawiają wrażenie mocno wychudzonych, a zmarszczki po bokach ust bardzo wyraźnie im się rysują. Mają duże uszy, wąskie i krótkie nosy oraz olbrzymie oczy w kolorach od bardzo jasnego i jaskrawego żółtego do ciemnego złota. Skórę mają gładką i jasną, choć nie białą, a włosy w odcieniach żółci, lub blondu. Na twarzach i ciele nie rośnie im owłosienie. Mogą dożyć 140 lat.  
Dostępne klasy: Kapłan (Saera, Vivera), Sługa Boży (Ither, Merena, Unises), Paladyn (Saera, Vivera), Czempion (Ither, Merena, Unises), Szaman, Druid, Wojownik, Barbarzyńca, Bard, Skrytobójca, Mnich, Łowca, Zwiadowca  
Znani bogowie: Ither \- 1, Saera \- 1, Merena \- 1, Unises \- 1, Vivera \- 1  
Języki: Wspólny  
Relacje: Bardzo dobrze dogadują się z haituvami i ludźmi, chociaż nie podoba im się bardzo niski poziom dążenia haituv do reprodukcji. Nie mają też zwykle problemu ze znalezieniem wspólnego języka z elfami, litorami i taikanami, chociaż przeszkadza im, że elfy i litory bywają zbyt porywczy i o ile podoba im się ich nienawiść do ras, których sami nie cierpią, uważają, że otwarta agresja wobec iltów, diebenów, czy sortajów, czy liskotów może przysporzyć więcej problemów, niż pożytku. Serdecznie nie cierpią iltów, jako istot stroniących od słońca, których dusze z perspektywy valotów są równie czarne, jak ciemność, którą się otaczają. Nie potrafią dojść do porozumienia również z diebenami, sortajami i liskotami, rasami o wątpliwej moralności, która mocno kłóci się z wartościami wyznawanymi przez większość valotów. Zwłaszcza fakt, że dość często wchodzą w układy z nieumarłymi. Choć wśród większości ras można znaleźć osoby o takich poglądach, Valoci są jedyną rasą, której niemal wszyscy przedstawiciele aktywnie zwalczają nieumarłych \- poruszające się abominacje stanowiące obrazę dla życia i prokreacji. Przez wzgląd na ostatnie zmiany i rosnącą społeczną akceptację wobec nieumarłych powoli przestają dogadywać się z kimkolwiek i najpewniej albo sami będą musieli się do nieumarłych przekonać, albo wyprowadzić ze wspólnych społeczności i założyć własne enklawy.

Ilty  
Opis: Rasa, która zwykle za dnia śpi w ciemnych i cichych domach, podziemiach, lub jaskiniach, a funkcjonuje nocą. Słońce i światło nie krzywdzi ich, ale sprawia silny dyskomfort i oślepia. Mówią bardzo niechętnie, ale dzięki wzrokowi pozwalającemu im widzieć w ciemności mogą porozumiewać się na migi, co stanowi ich podstawową formę komunikacji. Są wszystkożerni i dzięki bardzo szybkiemu metabolizmowi muszą jeść często i dużo. Poruszają się bezszelestnie i szybko, a kierują zwykle własną przyjemnością i wygodą, które przedkładają ponad dobra materialne. Bez problemu rozumieją jednak, że tymczasowa niewygoda może prowadzić do większej wygody w przyszłości. Jeśli coś robią, robią to bardzo gorliwie i jak najszybciej, żeby móc wrócić do strefy komfortu. Choć rozmnażają się płciowo, żadna płeć nie jest w ciąży, a dziecko rozwija się w postaci czegoś, co z początku przypomina grzybnię i dopiero z czasem przybiera humanoidalne kształty. Na tym etapie żywią się poprzez rozkładanie materii biologicznej znajdującej się w ziemi.  
Wygląd: Mierzą od 95 do 110 centymetrów, a ważą zazwyczaj między 15, a  24 kilogramy, przy czym kobiety i mężczyźni nie różnią się pod tym względem. Charakterystyczne jest również to, że kobiety nie mają drugorzędnych cech płciowych i wizualnie można je rozpoznać jedynie po rysach twarzy. Ich głowy kształtem są dość owalne, ale twarze mocno wyciągnięte do przodu, przez co wielkie, wyłupiaste i wodniście blade oczy znajdują się lekko po bokach, a spory nos bardzo mocno wystaje do przodu. Mimo mocno wyłupiastego kształtu ich oczy posiadają normalne powieki i co bardzo charakterystyczne nie odbijają światła, o ile jest go bardzo mało. Mają bardzo maleńką i trochę cofniętą żuchwę, przez co usta są skierowane trochę do dołu. Długie i spiczaste, ale szerokie uszy potrafią skierować się w stronę dźwięku, żeby lepiej go słyszeć. Włosy mają wyłącznie na głowie i zawsze są one czarne, a matowa skóra przybiera bardzo ciemne odcienie niebieskiego, zieleni, fioletu i wszystkiego pomiędzy. Mogą dożyć 50 lat.  
Dostępne klasy: Sługa Boży (Viona, Kena), Czempion (Viona, Kena), Czarownik, Czarnoksiężnik, Czarodziej, Psion, Wojownik, Barbarzyńca, Łotrzyk, Skrytobójca, Mnich, Łowca, Zwiadowca  
Znani bogowie: Viona \- 1, Kena \- 1, Ahana \- 1, Eheia \- 1, Eesir \- 1  
Języki: Wspólny  
Relacje: Najlepiej dogadują się z diebenami, chociaż diebeny sądzą inaczej. Niewiele gorzej potrafią się dogadywać z nieumarłymi, pakanami i calunami, choć ci ostatni są nieprzyjemnie głośni, a od pakani, mimo porozumienia na płaszczyźnie deklaracji i działań, wyczuwają pewną nieokreśloną niechęć, co z kolei ogranicza zaufanie ze strony iltów. Są również otwarci na kontakty z wendigo i tartunami, choć to oczywiste, że przy kontaktach z nimi obowiązuje najwyższa ostrożność. Dogadaliby się pewnie również z huiari, ale czują wysoce rozwiniętą niechęć do istot, które mogą się pod nich podszywać. Najbardziej na świecie nienawidzą valotów, którzy wszędzie próbują wciskać światło i na pewno po cichu wspierają te przeklęte elfy i litorów, którzy potrafią bez powodu zaatakować ilta za sam fakt bycia iltem. Bardzo nie lubią i kompletnie nie dogadują się również z ludźmi, haituvami i krasnoludami, którzy kierują się jakimiś kodeksami moralnymi, czy innymi bredniami.

Litory  
Opis: Rasa ziemnowodna. Mogą przebywać pod wodą godzinami, zanim będą musieli wynurzyć się po powietrze, ale po godzinie zaczyna być to dość nieprzyjemne. Na lądzie muszą dbać o nawilżenie skóry, bo zbytnio wysychając sprawia im dyskomfort, a nawet ból. Najlepiej czują się mogąc zachować balans między przebywaniem w wodzie i poza nią. W ich kulturze istnieje koncept Skoku Oczyszczania, który polega na zeskoczeniu do zbiornika wodnego z wysokości przynajmniej dwudziestu metrów. Wierzą, że taki ryzykowny skok jest w stanie zmyć z nich winy, których chcą się pozbyć. Lubią jasne kolory i dobrze oświetlone przestrzenie. Większość z nich jest przekonana, że nieprzyjemne sny są ostrzeżeniem przed niebezpieczeństwami, które czychają w przyszłości i mają za zadanie przygotować ich i ochronić przed zagrożeniem. Chętnie imają się zawodów artystycznych, lub innych, które pozwolą im coś tworzyć.  
Wygląd: Ich wzrost sięga od 170 do 195 centymetrów, a ich przeciętna waga plasuje się między 55, a 90 kilogramów. Mają wyjątkowo elastyczne i lekkie kości. Kobiety są statystycznie minimalnie niższe i umiarkowanie lżejsze od mężczyzn, ale odwrotne przypadki nie są rzadkością. Mają raczej nieduże i dość trójkątne głowy, a po bokach stosunkowo długiej szyi skrzela. Oczy również mają mniej więcej trójkątne, skierowane najostrzejszym wierzchołkiem na boki i trochę w górę, a ich kolor waha się między niebieskim, a cyjanem. Poza zwykłą powieką mają też błonę migawkową, którą zasłaniają oczy pod wodą, jeśli występuje w niej zasolenie. Nosy nieduże, usta szerokie, a kości policzkowe wyraźne. Mają bardzo duże i specyficzne uszy, które mogą składać segmentami, albo całkowicie złożyć i przyłożyć do głowy i szyi, aby w wodzie zoptymalizować swój opływowy kształt. Ich skóra jest śliska i potrafi przybierać różne odcienie koloru morskiego, czasem z plamami innych odcieni. Włosy w różnych odcieniach granatu, albo ciemnego niebieskozielonego są wyjątkowo śliskie, a po wyjściu z wody wystarczy je otrzepać i są już suche. Poza nimi owłosienia brak. Kończyny mają dość długie, a między palcami u rąk i nóg częściowe błony. Mogą dożyć 50 lat.  
Dostępne klasy: Kapłan (Saera), Sługa Boży (Unises, Ither, Merena), Paladyn (Saera), Czempion (Unises, Ither, Merena), Czarownik, Czarnoksiężnik, Szaman, Druid, Barbarzyńca, Bard, Łotrzyk, Łowca  
Znani bogowie: Unises \- 1, Ither \- 1, Inera \- 1, Saera \- 1, Merena \- 1  
Języki: Wspólny  
Relacje: Najczęściej doskonale dogadują się z elfami, które wesprą w walce bez zastanowienia i roztrząsania, kto zaczął. Dobrze dogadują się również z haituvami, taikanami i valotami, chociaż haituvi są w ich mniemaniu trochę zbyt zachowawcze i stoickie, taikani bywają amoralni, a valoci atakują wszystkich nieumarłych, nie zastanawiając się, czy ci konkretni nieumarli rzeczywiście na to zasługują. Kara potrzebuje winy, a przecież w przeciwieństwie do na przykład diebenów, historia zna przypadki nieumarłych służących ogólnie pojętym siłom dobra i sprawiedliwości. Nienawidzą diebenów, sarjaków, liskotów i iltów, ale sami nie atakują ich bez powodu, choć jeśli akurat przebywają z elfami, nie sprzeciwiają się takim pomysłom. Nie potrafią się dogadać z mustokami, tulevatami, gnomami i sortajami, którzy są zbyt poukładani i traktują prawo jako coś więcej, niż uprzejme wskazówki, jednocześnie nie rozumiejąc, że zło nie musi być z tym prawem sprzeczne, a i tak należy je ukarać.

Taikani  
Opis: Rasa, której przedstawiciele, jak się uważa, żyją w dwóch światach równolegle. Gdy ich ciała śpią, umysły ponoć przenoszą się do świata baśni. Większość z nich wierzy, że nic nie dzieje się przez przypadek, wszystko jest częścią większego planu, a każdy ma w tym planie rolę, którą ma wypełnić. Jednocześnie przekonani są, że nie jest to rola, która ich ogranicza, ale która jest nakreślona specjalnie dla nich i jest bezpośrednią konsekwencją tego, kim są. Z zasady charakteryzują się zapałem, są energiczni, lubią zabawę, żarty i beztroskę, a także romanse i dreszczyk emocji towarzyszący niebezpieczeństwu.  
Wygląd: Mierzą od 140 do 150 centymetrów i ważą średnio między 40, a 55 kilogramów, przy czym mężczyźni są zwykle wyżsi i słabiej zbudowani od kobiet. Głowy mają dość trójkątne, ale przez kształt i ułożenie wielkich uszu wyglądają bardziej jak strzałka w dół. Mają niewielkie żuchwy i szerokie usta, sprawiające najczęściej wrażenie uśmiechniętych. Mają drobne, ale długie nosy i oczy w kształcie migdałów, które mogą mieć niemal dowolny kolor, podobnie jak ich włosy. Zarost im nie rośnie, a skóra jest zwykle kremowa w ciemniejsze plamki. Mogą dożyć 60 lat, a po śmierci trafiają podobno w to samo miejsce, gdzie w trakcie snu.  
Dostępne klasy: Sługa Boży (Unises, Ither, Kena), Czempion (Unises, Ither, Kena), Czarownik, Czarnoksiężnik, Druid, Barbarzyńca, Bard, Łotrzyk, Skrytobójca  
Znani bogowie: Inera \- 1, Saera \- 1, Unises \- 1, Ither \- 1, Kena \- 1  
Języki: Wspólny  
Relacje: Choć najwięcej wspólnego mają z calunami i pakanami, najłatwiej im dogadać się z elfami, ponieważ pakani zupełnie inaczej rozumią przeznaczenie i są denerwująco bierni, a twierdzenie calunów, że ich sny są równie prawdziwe, co sny taikan, bywa męczące. Dobrze dogadują się również z litorami i valotami, chociaż niespecjalnie rozumieją ich moralność, a awersja valotów wobec nieumarłych oparta wyłącznie na byciu nieumarłymi jest dla nich absolutnie nie do zrozumienia. Podobnie zresztą, jak agresja elfów i litorów wobec niektórych ras, która potrafi czasem zaburzyć bardzo dobre poza tym relacje. Nie są za to w stanie znaleźć absolutnie żadnej płaszczyzny porozumienia z kranoludami, sarjakami, mustokami, tulevatami, gnomami, sortajami i liskotami, ponieważ rasy te reprezentują zewnętrzny porządek, który zdaniem taikan nie może przysłonić wewnętrznego porządku i zgodności z własnym ja, która prowadzi ich do przeznaczenia.

Liskoci  
Opis: Rasa spokrewniona ze smokami i wiele ich społeczności z nimi współpracuje. Wierzą w rządy silnej ręki, dlatego tak łatwo podporządkowują się smokom. Cenią sobie wiedzę, którą mogą między innymi zdobywać w zamian za służbę u żyjących setki lat kuzynów. Przez wzgląd na to pokrewieństwo zwykle uważają się za lepszych od innych, których często potrafią bez skrupułów wyzyskiwać i wykorzystywać. Niektórzy z nich potrafią przybierać pomniejszą skrzydlatą formę, dzięki czemu mają wyższy status w społeczności. Dość popularne jest wśród nich zainteresowanie magią teleportacyjną i nekromancją. Ich społeczności charakteryzują się dość spolaryzowanym podejściem do bólu, cierpienia i śmierci. Kiedy część z nich to dość radykalni masochiści, czerpiący z nich przyjemność, inni robią wszystko, żeby je od siebie odsunąć, choćby kosztem części tożsamości. W rezultacie właśnie spośród tej rasy wywodzi się największy odsetek liczów.  
Wygląd: Mierzą od 160 do 180 centymetrów, a ważą zwykle między 120, a 160 kilogramów. Są przy tym raczej smukli, ale mają bardzo ciężkie kości i łuski. Kobiety są nieznacznie niższe i lżejsze od mężczyzn. Ich głowy są lekko podłużne, a poniżej wyrazistych kości policzkowych trójkątne. Mocny zarys czaszki jest widoczny na pierwszy rzut oka, a na niektórych krawędziach kości rosną im kolce. Uszy mają cienkie, przypominają nieco szerokie i krótkie ostrza skierowane szpicem do góry i ostrą krawędzią do tyłu. Nie mają nosów w klasycznym tego słowa znaczeniu, ale ledwie zarysowany wzgórek z nozdrzami. Ich łuski mogą przybierać bardzo różnorodne, ale wyraziste i intensywne kolory. Włosy zwykle w podobnym kolorze, co łuski, ale ciemniejszym. Zdarza się jednak, że trafiają się na zasadzie dobranych z gustem kontrastów, całkiem, jakby jakaś siła dbała, żeby żaden liskot nie skończył z czerwonymi łuskami i zielonymi włosami. Na ciele i twarzy nie posiadają owłosienia. Albo wiecznie mrużą oczy, albo ich oczy po prostu tak wyglądają. Najczęściej są czarne jak pustka, ale czasem przywodzą na myśl płomienie w kolorze dopasowanym do włosów. Mogą dożyć 1000 lat.  
Dostępne klasy: Kapłan (Deros), Sługa Boży (Yreus), Paladyn (Deros), Czempion (Yreus), Czarodziej, Psion, Wojownik, Skrytobójca, Mnich, Zwiadowca  
Znani bogowie: Gisstus \- 1, Deros \- 1, Carisa \- 1, Thasara \- 1, Yreus \- 1  
Języki: Wspólny, smoczy  
Relacje: Poza smokami, spośród ras humanoidalnych, najlepiej dogadują się z sortajami, choć uważają, że ci często nie znają swojego miejsca i próbują zagarnąć dla siebie więcej władzy, lub potęgi, niż są w stanie skutecznie kontrolować. Bardzo łatwo znaleźć im też wspólny język z większością rozumnych nieumarłych, a bezrozumnych bardzo chętnie tworzą i wykorzystują. O ile ich stosunek do wendigo i tartunów nie odbiega od powszechnego, w pewnych okolicznościach są otwarci na kontakty i relacje z huiari. Nie cierpią za to nieobliczalnych calunów i taikan, nastawionych do siebie agresywnie elfów i litorów, a także przesiąkniętych archaiczną moralnością ludzi, haituv i valotów. Pakan z kolei nie traktują poważnie i nie uważają za kogoś, kim w ogóle warto zawracać sobie głowę.

Wampiry  
Opis: Dumne istoty pozbawione organicznego aspektu emocji, przez co często pretensjonalne i przesadnie je okazujące, z tendencją do teatralności. Nikt nie rodzi się wampirem, a szok podczas przemiany zwykle jest tak duży, że potrafi drastycznie zmienić osobowość i charakter przemienionej osoby, a także odblokować pełny potencjał intelektualny, ponieważ rozsądek nie podlega już cielesnym podnietom. Pozostawia też po sobie dojmujące uczucie braku, które nigdy ich nie opuszcza. Wraz ze śmiercią ciała często przychodzi oziębłość oraz skłonność do wyzysku i hierarchii, która choć trochę pozwala uporządkować nową rzeczywistość. Spośród innych nieumarłych wyróżniają się wielkim sentymentem i nostalgią do nocy jako pory, kiedy przeszli do nieżycia. Światło słoneczne znacząco je osłabia, a w przypadku mało wytrzymałych jednostek może nawet je krzywdzić. Potrafią zamieniać się w mgłę i stado nietoperzy, żywią się krwią, ale są wrażliwe na czosnek i jak wszyscy nieumarli na wodę poświęconą przez kapłanów bóstw powszechnie uznawanych za dobre. Rany zadane zwykłą bronią nie są dla nich groźne, ale magia, srebro i wymienione już rzeczy mogą doprowadzić ich do drugiej, ostatecznej śmierci.  
Wygląd: Po przemianie ich wzrost się nie zmienia, ale ze względu na to, że nie potrzebują już normalnego pożywienia, stają się lżejsi o zawartość układu pokarmowego. Ich skóra i włosy stają się matowe i przybierają odcienie szarości, a oczy przyjmują barwę srebrną, lub stalową. Jako nieumarli nie mają górnego limitu czasu życia.  
Znani bogowie: Thasara \- 1, ci co za życia  
Języki: Wspólny  
Relacje: Od zawsze będące trochę z boku niespecjalnie przejmowały się relacjami z kimkolwiek, ponieważ i tak nie były akceptowane w szerszym społeczeństwie. Potrafiły jednak zawsze w razie potrzeby dogadać się z liskotami i sortajami, a jeśli sytuacja je zmuszała, nawet z diebenami. Obecnie są nieumarłymi, wobec których śmiertelnicy mają największe obawy i wątpliwości, ale obie strony bardzo powoli uczą się ufać sobie nawzajem. Jednak pełną asymilację stawiają pod znakiem zapytania valoci, uważający sam fakt istnienia nieumarłych za zło. Są to też jedyni faktyczni wrogowie wampirów, którymi te muszą się przejmować. W kontaktach z innymi rasami wystarcza rozsądek i trochę dobrej woli z obu stron.

Licze  
Opis: Potężni użytkownicy magii, którym udało się osiągnąć niewrażliwość na upływ czasu i potencjalną nieśmiertelność poprzez uwięzienie swojej duszy we własnym ciele, które na potrzeby tego procesu musieli rytualnie zabić. Ciało to co prawda psuje się, gnije i odpada, pozostawiając w końcu sam szkielet, ale moc magiczna utrzymuje ich w jednym kawałku i pełnej sprawności. Mają tendencję do otaczania się licznymi nieumarłymi sługami, którzy są im bezwzględnie posłuszni. Nie jest powiedziane, że każdy licz musi być szalonym tyranem dążącym do władzy nad światem, ale część z nich zostaje liczami właśnie po to, aby łatwiej było im to osiągnąć. Mają zwykle ogromną wiedzę, a w magii nikt nie może im dorównać. Są też odporni na ataki zwykłą bronią i podstawowe zaklęcia, ale zaklęta broń, zaawansowana magia i woda poświęcona przez kapłana jednego z bóstw powszechnie uznawanych za dobre, mogą ich ostatecznie uśmiercić.  
Wygląd: Po przemianie dość szybko zaczynają tracić części organiczne, a co za tym idzie masę. Gdy organiczna otoczka odpadnie, pozostaje sam szkielet, który u większości ras waży mniej więcej od 11% do 15% oryginalnej wagi. Jako nieumarli nie mają górnego limitu czasu życia.  
Znani bogowie: Thasara \- 1, ci co za życia  
Języki: Wspólny  
Relacje: Do niedawna wykluczeni ze społeczeństw zamykali się w niedostępnych miejscach, by tam napawać się swoją potęgą i władzą nad armiami nieumarłych, jednak kiedy było im to niezbędne, potrafili zawsze dogadać się z liskotami, diebenami i iltami, a w ostateczności nawet z sortajami. Teraz stanowią podstawową linię kontaktu świata śmiertelników i świata nieumarłych jako ci spośród nich, którzy zarówno posiadają pewnego rodzaju ciało, jak i organiczne kwestie nie zmuszają ich do picia krwi istot, z którymi mają się dogadać. Wielu z nich korzysta z nowej pozycji, by potencjalnie rozszerzyć swoje wpływy wchodząc w role dyplomatów i ambasadorów. Nienawidzą jedynie valotów, którzy próbują ich zabijać, ale siłą rzeczy jest to nienawiść zasłużona.

Duchy  
Opis: Dusze, które po śmierci pozostały w świecie materialnym na skutek silnego poczucia, że mają niedokończone sprawy. Zwykle cierpią, znajdując się w tym stanie, ale nie mogą, nie potrafią, lub nie chcą przejść dalej. Spośród nieumarłych zachowują najwięcej oryginalnych cech istot, którymi były za życia i praktycznie nienaruszoną osobowość. Nie da się ich skrzywdzić fizycznie i można je uszkodzić jedynie magią, ale nie mogą też wchodzić w interakcje ze światem materialnym, o ile nie przejmą kontroli nad jakimś ciałem \- żywym, lub martwym. Jak na wszystkich nieumarłych działa na nie poświęcona woda, ale przeciwko duchom, które można by nazwać dobrymi, działa dla odmiany woda przeklęta przez jakieś bóstwo powszechnie uznawane za złe, lub amoralne (z wyjątkiem Thasary).  
Wygląd: Po śmierci zachowują dokładnie ten sam rozmiar i kształt, co w chwili zgonu, jednak jako istoty niefizyczne nie posiadają masy. Po opętaniu osoby, lub zwłok, nie wpływają na ich kształt i wagę. Jako nieumarli nie mają też górnego limitu czasu życia.  
Znani bogowie: Thasara \- 1, ci co za życia  
Języki: Wspólny  
Relacje: Dotąd były na tyle z boku i tak bardzo oddzielone od rozumnych ras i siebie nawzajem, że dopiero uczą się, kim tak naprawdę są i kim chcą być. Ich kontakty z innymi rasami to kwestia bardzo indywidualna, ale mają jednego wspólnego wroga z przymusu \- valotów, którzy chcą zafundować im śmierć, tym razem ostateczną.

Pomniejsi nieumarli  
Opis: Bezmyślne i bezwolne istoty powołane do nieżycia bez dusz, które zamieszkiwały ich ciała przed śmiercią. Zwykle bezwzględnie posłuszni sile, która ich stworzyła. Jeśli źródło tej siły zniknie, na przykład zginie nekromanta, który ich ożywił, wracają do stanu sprzed ożywienia.

Likantropy generalnie  
Opis: Likantropia roznosi się poprzez ugryzienie. Jeśli układ odpornościowy ofiary nie poradzi sobie z nośnikami klątwy zawartymi w ślinie, lub jadzie, w dość krótkim czasie stanie się ona kolejnym likantropem. Za dnia przebywają w formie humanoidalnej, ale nocą, gdy księżyc jest widoczny, potrafią również przybierać postać zwierzęcia, lub hybrydową. Pełnia sprawia, że budzi się w nich głód, a zwierzęca strona ich osobowości rośnie w siłę i jeśli wola likantropa jest zbyt słaba, przejmuje nad nim kontrolę popychając go do bezwarunkowego zaspokojenia głodu.

Wilkołaki  
Opis: Wykazują skłonności terytorialne i najczęściej łączą się w watahy, na których czele stoi Alfa. Często charakteryzuje je duma i wytrwałość, a ich Alfa to jedyny autorytet, jaki uznają.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, wilczy  
Relacje: Unikają życia w zorganizowanych społeczeństwach, tworząc własne enklawy. Trzymają się z boku i chcą, żeby inni również trzymali się z dala od nich.

Krukołaki  
Opis: Mają instynkt łączenia się w pary na całe życie i osiedlenia się w stałym miejscu, ale zanim to nastąpi, na ogół łączą się w polujące razem stada. Statystycznie najinteligentniejsze i najbardziej cywilizowane spośród likantropów.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, kruczy  
Relacje: Żyją pośród zwykłych społeczeństw, zachowując sympatie i antypatie sprzed przemiany, ale ponadto wszędzie zwęszą swoich i zwykle niezależnie od rasy okażą wobec nich przyjazne zamiary. Przynajmniej na początku.

Niedźwiedziołaki  
Opis: Samotnicy, którzy unikają zarówno humanoidów, jak i likantropów, a nawet innych niedźwiedziołaków. Sporadycznie zdarza się para niedźwiedziołaków żyjąca razem, ale nawet wtedy zachowują sporą niezależność. Większość z nich charakteryzuje wyjątkowa witalność, siła i spokój.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, niedźwiedzi  
Relacje: Nie utrzymują relacji. Z nikim. Z żadną inną rozumną rasą, ani ze sobą nawzajem.

Pająkołaki  
Opis: Nie wykazują tendencji do tworzenia grup, ani do samotnictwa, ale lubią otaczać się wielkimi pająkami, które traktują trochę jak zwierzęcych pupili, a trochę jak dzieci. Zwykle pracowici, cierpliwi i rozsądni, ale często również podstępni i bezwzględni. Są wyjątkowo jadowici.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, pajęczy  
Relacje: Raczej unikają kontaktu z cywilizowanymi społecznościami, ale zaskakująco dobrze potrafią dogadywać się z nieumarłymi.

Kotołaki  
Opis: Najczęściej wolą trzymać się sami i chodzić własnymi drogami. Niezwykle zwinni i cisi, mordercy idealni. Na ogół egoistyczni i dążący do własnej wygody. Często lubią poznęcać się nad kimś nawet, jeśli nie są głodni.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, koci  
Relacje: Większość z nich to socjopaci żyjący samotnie w dużych miastach, ale lubiący je opuszczać. Nie dbają o relacje z kimkolwiek i utrzymują je na najniższym możliwym poziomie koniecznym do zapewnienia sobie komfortowego życia.

Lisołaki  
Opis: Potrafią zarówno żyć samotnie, jak i łączyć się w rodziny, albo stada. Najsprytniejsi i najprzebieglejsi spośród likantropów. Podstępni, choć najczęściej nie okrutni. Zwykle mają jakieś ukryte motywy, ale raczej nie krzywdzą, jeśli nie mają konkretnego powodu.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, lisi  
Relacje: Zwykle zachowują sympatie i antypatie sprzed przemiany. Zdarza im się zarówno żyć wśród normalnych społeczności, jak i na uboczu, albo we własnych enklawach.

Wężołaki  
Opis: Niektórzy są samotnikami, ale większość woli żyć w grupach. Przylgnęła do nich opinia zdradliwych, a w powszechnym przekonaniu nie można im ufać, co często sprawia, że rzeczywiście tacy się stają. Unikają kontaktu z kimkolwiek innym, niż inne wężołaki.  Bardziej jadowici nawet od pająkołaków.  
Wygląd:  
Znani bogowie: Carisa \- 1, ci sprzed przemiany  
Języki: Wspólny, wężowy  
Relacje: Nauczyli się nikomu nie ufać, ponieważ nikt nie ufa im. W związku z tym trzymają się z daleka od kogokolwiek, żyjąc w swoich enklawach.

Tulevaty  
Opis: O ile istnieją inne rasy, którym zależy na rodzinie i więziach z nieco szerszą społecznością, nikt inny nie odczuwa fizycznego bólu, gdy zbyt długo jest oderwany od innych przedstawicieli swojej rasy. Mędrcy zastanawiają się, dlaczego tak jest, ale w ich przypadku sprawność zarówno psychiczna, jak i fizyczna, rośnie wraz z ich ilością i jakością relacji między nimi, a spada, gdy zostają oddzieleni, albo pojawią się między nimi zatargi. W ekstremalnych sytuacjach podobno zdarzało się, że samotny przedstawiciel tej rasy po jakimś czasie po prostu umierał. Ze względu na to wszystko, poza zewnętrznie panującymi prawami tworzą własne zasady, aby ich społeczności były jak najsilniejsze jednomyślnością. Bardzo cenią sobie również mądrość, wiedzę i uczciwą pracę.  
Wygląd: Ich wzrost waha się między 160, a 170 centymetrów a waga oscyluje od 65 do 75 kilogramów. Kobiety i mężczyźni nie różnią się pod tym względem. Mają dość podłużne głowy i raczej wąskie żuchwy, niezbyt charakterystyczne usta i nosy, ale ich uszy są duże i oklapłe jak u niektórych zwierząt. Mają spore oczy o imponująco bujnych i długich rzęsach, a ich tęczówki, jak teoretycznie sama nazwa powinna wskazywać, są tęczowe. Na twarzach i ciałach nie rośnie im zarost, a włosy miewają najróżniejsze, ale dość ciemne kolory, mocno kontrastujące z jasną cerą. Mogą dożyć 50 lat.  
Znani bogowie: Merdos \- 1, Nuvva \- 1, Yreus \- 1, Anlena \- 1, Wireus \- 1  
Języki: Wspólny  
Relacje: Przez wzgląd na silnie sformalizowany i skodyfikowany charakter ich społeczności, doskonale dogadują się z krasnoludami. Łatwo też znaleźć im wspólny język z gnomami, sarjakami i mustokami, chociaż tendencja gnomów do kombinowania sprawia, że podchodzą do nich trochę nieufnie, a kompletny brak emocji u sarjaków trochę ich przeraża. Bardzo nie lubią calunów, elfów, litorów i taikan jako osobników z zasady przynoszących zamieszanie i chaos, które mogą wprowadzić niezgodę nawet między członków rodziny. Brzydzą się też pakanami i ich rozbuchanym lenistwem.

Smoki  
Opis: Potężne, skrzydlate istoty o wielkiej wiedzy, a jeszcze większej pysze i arogancji. Potrafią przybierać postać humanoidów, ale robią to niechętnie i tylko wtedy, gdy wymaga tego jakiś cel, który sobie postawią. Lubią zadawać ból, wyzyskiwać słabsze rasy i gromadzić ich bogactwa dla samej tylko chęci posiadania ich, ale wiedzą, że nieskrępowane folgowanie swoim zachciankom w końcu ściągnie na nie gniew wszystkich innych ras, które zjednoczą się, żeby je zabić. Historia zna takie przypadki, więc smoki w rezultacie przypominają bardziej potężnych tyranów, którzy co prawda łupią swoich poddanych, ale dbają też o to, żeby na ich ziemiach nie robił tego nikt inny.  
Wygląd: Rozmiar smoków bardzo silnie zależy od ich wieku. Pisklęta są zwykle długie na około 2 metry z ogonem i nieco ponad metr mierząc bez ogona, a ważą między 300, a 350 kilogramów. Dorosłe smoki osiągają zwykle od 15 do 20 metrów długości, ale te najstarsze mogą dochodzić do nawet 40 metrów. Ich waga również różni się w zależności od wieku i dla dorosłych smoków wynosi zwykle około 200 kilogramów na metr długości, czyli średnio od 3 do 4 ton. Samice nie różnią się od samców pod względem długości, ale zwykle są trochę smuklejsze i lżejsze.  
//TODO  
 Mogą dożyć 3000 lat.  
Języki: Wspólny, smoczy  
Relacje: Tak naprawdę z nikim nie muszą się dogadywać, ale lubią, aby ktoś im usługiwał, dzięki czemu potrzeby ich i liskotów wzajemnie się uzupełniają. Dość trudno im dojść do porozumienia z większością innych ras, ale w przypadku elfów, litorów i calunów bywa to wyjątkowo skomplikowane, a czasem niemożliwe ze względu na ich częste działanie wbrew logice i zdrowemu rozsądkowi. Dlatego też smoki na swoje tereny obierają zwykle rejony, gdzie jest ich możliwie najmniej, co zmniejszy szansę na potencjalne próby uratowania okolicy od strasznego smoka.

Skurille  
Opis: Różnorodni mieszkańcy baśniowego świata, do którego taikani przenoszą się we śnie. Występują w różnych rozmiarach i kształtach, z zasady są bardziej nieprzewidywalni i nieco szaleni, ale najczęściej życzliwi, choć nie stoi to w sprzeczności ze złośliwością.  
Wygląd:  
Języki: Wspólny  
Relacje: Niespecjalnie miewają okazję nawiązywać relacje z kimkolwiek innym, niż taikani, bo poza światem baśni przebywają zwykle wyłącznie w celach narzuconych im przez magów. Bardzo sporadycznie pojawiają się z własnej woli, między innymi dlatego, że inne światy poza ich rodzimym na ogół wydają im się szalenie nieatrakcyjne.

Tartuny  
Opis: Rasa wykluczona z normalnych, zdrowych społeczeństw, ponieważ roznoszą choroby i często czerpią z tego przyjemność. Pielęgnują choroby, roznoszą zarazy, kochają rozkład. Dążąc do niego ignorują wszystko inne, kłamią, podszywają się pod przedstawicieli innych ras i posuwają się do skomplikowanych podstępów. Nie używają ognia, ani światła, a wszystko, co robią, starają się robić po cichu, ponieważ twierdzą, że wtedy są w stanie usłyszeć gnicie świata.  
Wygląd: Mierzą od 100 do 120 centymetrów i ważą zwykle od 18 do 30 kilogramów. Kobiety są zwykle sporo niższe od mężczyzn i ważą zdecydowanie mniej.  
Kształt głowy  
Oczy  
Uszy  
Nos  
Usta  
Skóra  
Owłosienie  
Dłonie  
Kończyny  
//TODO  
 Mogą dożyć 60 lat.  
Znani bogowie: Ahana \- 1, Oara \- 1, Eesir \- 1, Eheia \- 1, Viona \- 1  
Języki: Wspólny  
Relacje: Większość cywilizowanego świata uważa je za potwory i atakuje bez ostrzeżenia, ale caluni zwykle podchodzą do nich z zainteresowaniem zamiast agresji i choć szanse na wykorzystanie caluna do własnych celów są małe, tartuny to cenią i wobec calunów zdarza im się okazywać litość. Co innego diebeny i ilty, z nimi potrafią się czasem dogadać, ale mają świadomość, że nie ma to nic wspólnego z sympatią i zostaną zdradzeni, jeśli tak będzie wygodniej “cywilizowanym”. Dlatego też caluni są wyjątkiem, a diebeny i ilty nie mają co liczyć na miłosierdzie. Oczywiście wspólny wróg w pewien sposób łączy, dzięki czemu zdarza im się współpracować z huiari i wendigo, ale o ile do wendigo żywią pewnego rodzaju sympatię, huiari traktują jako sojuszników z przymusu.

Huiari  
Opis: Rasa istot mogących dowolnie modyfikować swój wygląd oraz imitować zarówno przeciętnego przedstawiciela innej rasy, jak i konkretną osobę. Poza głodem fizycznym odczuwają też głód nowych twarzy i czują dyskomfort przebywając zbyt długo w jednej postaci. Mają wrodzoną zdolność teleportowania się na małe dystanse raz na jakiś czas. Należą do istot ceniących sobie wiedzę i informacje, które wykorzystują do realizacji własnych zamierzeń. Dążąc do celu nie przebierają w środkach, podszywając się pod innych, kłamiąc, a czasem nawet mordując kogoś, aby go na jakiś czas zastąpić. Niektórzy uważają, że są jednym ze sposobów, w jaki wszechświat radzi sobie z dążeniem do chaosu.  
Wygląd: W swojej naturalnej postaci, której niemal nigdy nie przybierają, mierzą od 180 do 190 centymetrów i ważą między 50, a 80 kilogramów. Kobiety i mężczyźni są w zasadzie identyczni i rozpoznają się po feromonach. O ile potrafią idealnie odwzorować wygląd dowolnego humanoida, ich waga nigdy się nie zmienia i może ich zdradzić. Gdy człowiek jest w stanie podnieść lentusa, najpewniej lentus jest tak naprawdę huiari.  
Kształt głowy  
Oczy  
Uszy  
Nos  
Usta  
Skóra  
Owłosienie  
Dłonie  
Kończyny  
//TODO  
 Mogą dożyć 110 lat.  
Znani bogowie: Carisa \- 1, Gisstus \- 1, Eesir \- 1, Oara \- 1, Ahana \- 1  
Języki: Wspólny  
Relacje: Gdy wiadomo, kim są, atakuje ich każdy poza diebenami i liskotami, którzy potrafią dostrzec potencjalną korzyść płynącą ze znajomości z huiari i nie budzi w nich ona moralnych oporów. Podszywając się pod przedstawiciela innej rasy w jakimś stopniu są w stanie dogadać się z kimkolwiek, ale jest to dla nich zwykle stresujące i nieprzyjemne, chyba że mowa właśnie o liskotach, czy diebenach. Co ciekawe, gdy sortaje nie wiedzą, z kim mają do czynienia, również bardzo dobrze dogadują się z huiari do czasu, gdy ich tożsamość wyjdzie na jaw. Poza cywilizowanymi rasami czasem współpracują z tartunami, albo wendigo, ale wśród nich również czują niechęć skierowaną w swoją stronę, więc nie robią tego chętnie.

Wendigo  
Opis: Rasa, której główną siłą napędową jest nienasycony, nigdy niezaspokojony głód, który potrafi choć na chwilę uśmierzyć jedynie mięso humanoidów. Przez większość cywilizowanych ras uznawani za potwory, ale niektórzy niechętnie przyznają, że są rozumną rasą. Prowadzą nocny tryb życia i polują w ciszy. Chyba poza demonami najbardziej znienawidzona rasa, często nawet błędnie uważana za podgatunek demonów. Gdy wendigo pojawia się w pobliżu osad humanoidów i zostanie wypatrzony, mieszkańcy przystąpią do ataku nie dając mu nawet okazji się odezwać.  
Wygląd: O ile podczas przemiany wszystkie przybierają podobny kształt, ich rozmiary mocno zależą od rasy, której przedstawicielem byli wcześniej. Zwykle trochę się wydłużają i drastycznie chudną, osiągając mniej więcej 110% wcześniejszego wzrostu i 70% wcześniejszej wagi.  
Kształt głowy  
Oczy  
Uszy  
Nos  
Usta  
Skóra  
Owłosienie  
Dłonie  
Kończyny  
//TODO  
 Mogą żyć nawet do  2,5 raza dłużej, niż wskazywałaby ich pierwotna rasa. Według jednej z historii, którymi rodzice straszą potomstwo, najstarszym wendigo jest mający ponad 20 tysięcy lat były lentus mierzący 3,5 metra, który przychodzi zjadać niegrzeczne dzieci.  
Znani bogowie: Eesir \- 1, Ahana \- 1, Carisa \- 1, Viona \- 1, Oara \- 1  
Języki: Wspólny  
Relacje: Zwykle są samotnikami, którzy unikają kontaktów z kimkolwiek, nawet z tartunami i huiari, również wykluczonymi ze społeczeństw. Gdy są sami, zwykle łatwiej im nawiedzać cmentarze i wyjadać zwłoki, chociaż czasami zdarza im się podejmować współpracę z innymi wendigo, albo z iltami. Mniej chętnie i bardzo sporadycznie wchodzą w układy z diebenami, którym nie ufają i boją się ich zdrady, tartunami, od których boją się czymś zarazić, oraz z huiari, którzy budzą odruchową nieufność w większości istot rozumnych. Robi się niezręcznie, gdy ktoś może wyglądać dokładnie tak, jak ty.

Rasy pochodzą od bogów. Khiarus był kiedyś jednym z nich, a demony jedną z ras podstawowych, tylko go wychujali.

Istoty spoza podstawowej rzeczywistości, które potężni użytkownicy magii mogą przyzwać:

- Każdy bóg ma swoją krainę, z której można przywoływać odpowiedniki chochlików, żywiołaków i planetarów

Główni przeciwnicy \- demony:

- 

Panteon Współpracujący:

- Zilos, Sędzia Sprawiedliwy \- Bóg porządku, ładu i sprawiedliwej kary. Reputacje zwracające jego uwagę: Cnotliwość, Hojność, Rzeczowość, Sprawiedliwość, Wytrwałość (7 rano)  
- Vivera, Władczyni Niebios \- Bogini odkupienia, odnowy i opowieści. Reputacje zwracające jej uwagę: Cnotliwość, Hojność, Opiekuńczość, Odwaga, Życzliwość (8 rano)  
- Saera, Mścicielka Pokrzywdzonych \- Bogini ochrony, żarliwości i kochanków. Reputacje zwracające jej uwagę: Życzliwość, Opiekuńczość, Pasja, Romantyzm, Impulsywność (9 rano)  
- Anlena, Strażniczka Porządku \- Bogini prawa, planowania i przeznaczenia. Reputacje zwracające jej uwagę: Szczerość, Rzeczowość, Sprawiedliwość, Wytrwałość (10 rano)  
- Custus, Pan Równowagi \- Bóg świętego spokoju, stałości i przetrwania. Jego uwagę zwracają: Spokój, Równowaga (Brak silnych tendencji na późnym etapie gry) (11 rano)  
- Eheia, Kapryśna Pani \- Bogini muzyki, niestałości i zapału. Reputacje zwracające jej uwagę: Urok Osobisty, Pasja, Romantyzm, Impulsywność (12 \- południe)  
- Deros, Bezduszny Włodarz \- Bóg tyranii, wyzysku i pychy. Reputacje zwracające jego uwagę: Bezwzględność, Chciwość, Egoizm, Rzeczowość, Sprawiedliwość (13 po południu)  
- Oara, Królowa Zdecydowanych \- Bogini chciwości, oszustwa i skupienia na celu. Reputacje zwracające jej uwagę: Bezwzględność, Chciwość, Egoizm, Zwodniczość, Wytrwałość (14 po południu)

Bogowie Bez Kościołów:

- Ither, Książę Świtów \- Bóg słońca, światła, szczęścia i radości. \- (Zainteresowanie graczem, gdy: Zostawianie zapalonych pochodni w miejscach, albo kożystanie z jakiegoś czaru imitującego światło słoneczne)  
- Kena, Pasjonatka Tu i Teraz \- Bogini przyjemności, pożądania i hedonizmu. \- (Zainteresowanie graczem, gdy: Kilka przejawów hedonizmu)  
- Merdos, Wspólny Kierunek \- Bóg wspólnoty, rodziny i społeczeństwa. \- (Zainteresowanie graczem, gdy: Kilka przejawów silnego utożsamiania się z jakąś wspólnotą)  
- Merena, Nieustępliwa Kreatorka \- Bogini stworzenia, magii i magicznej potęgi. \- (Zainteresowanie graczem, gdy: Znajomość przynajmniej X czarów, w tym przynajmniej 1 tworzący coś, a także minimalna wartość maksymalnej many równa 1000\)  
- Nuvva, Złota Panna \- Bogini handlu i uczciwej pracy. \- (Zainteresowanie graczem, gdy: Posiadanie jednocześnie jakiejś kwoty, lub przeciwdziałanie monopolowi, albo osiągnięcie przelicznika cen, przy którym zyskujesz kupując i sprzedając)  
- Unises, Oczyszczający Przypływ \- Bóg oceanów, nieba i oczyszczenia. \- (Zainteresowanie graczem, gdy: Wyrażana wiara w oczyszczającą moc wody, lub częste mycie siebie/rzeczy)  
- Viona, Skryta Opiekunka \- Bogini cieni, ciemności i ciszy. \- (Zainteresowanie graczem, gdy: Umiejętność skradania się, częste kożystanie z niej i spanie/przeczekiwanie dnia)  
- Wireus, Strażnik Tajemnic \- Bóg wiedzy, tajemnic i mistycyzmu. \- (Zainteresowanie graczem, gdy: Liczne wypowiedzi świadczące o mądrości i uduchowieniu oraz wiedza (wartość powyżej 70))  
- Yreus, Ostoja Udręczonych \- Bóg cierpienia, bólu i śmierci. \- (Zainteresowanie graczem, gdy: Wybieranie opcji dialogowych sugerujących, że życie do cierpienie, ból jest wszystkim, a na końcu i tak wszystkich nas czeka śmierć)


Bogowie Ukryci:

- Ahana, Niosąca Zgniliznę \- Bogini zarazy, rozkładu i entropii. \- (Zainteresowanie graczem, gdy: Doprowadzenie do rozniesienia się jakiejś zarazy)  
- Carisa, Księżycowa Heroldka \- Bogini przemian, portali i księżyca. Twórczyni likantropii. \- (Zainteresowanie graczem, gdy: Częste kożystanie z zaklęć teleportacyjnych, lub likantropia)  
- Eesir, Głodny Wędrowiec \- Bóg-ojciec wendigo, pan głodu. \- (Zainteresowanie graczem, gdy: Zjedzenie mięsa humanoida)  
- Gisstus, Ojciec Smoków \- Bóg smoków i wiedzy. \- (Zainteresowanie graczem, gdy: Wiedza (wartość powyżej 40\) oraz wyrażana sympatia wobec smoków)  
- Inera, Matka Wróżek \- Bogini snów i przeznaczenia. \- (Zainteresowanie graczem, gdy: Kilka przejawów wiary w logikę baśni, przeznaczenie, generalny oniryzm i ostateczne zwycięstwo dobra nad złem)  
- Thasara, Królowa Potępionych \- Bogini-opiekunka nieumarłych. \- (Zainteresowanie graczem, gdy: Wyrozumiałość i sympatia wobec nieumarłych, lub częste kożystanie z nekromancji)


  
Główny Zły:

- Khiarus, Pożeracz Dusz \- Bóg demonów i krwawej łaźni (24 \- północ)  
    
    
  


Portrety \- wszystko dla każdej rasy i płci, po kilka (3? 4?) razy:

- Kapłan, Sługa Boży, Paladyn, Czempion \- natchniona gęba  
- Wojownik, Barbarzyńca \- twarz nieugiętej siły i dumy, może gniewu  
- Druid, Szaman \- jakieś plemienne barwy i szmaty  
- Czarownik, Czarnoksiężnik, Czarodziej, Psion \- magia w mordzie  
- Bard, Łotrzyk, Skrytobójca \- cwaniaczek  
- Łowca, Zwiadowca, Mnich \- skupienie, spokój i cierpliwość na twarzy  
  


Profesje a wiedza o bogach:  
(Wiedza o Khiarusie jest dostępna wszystkim na poziomie 1\)  
(Różnica między kapłanem, a sługą bożym to dostępna lista bóstw i istnienie zorganizowanej religii, lub nie)  
(1 oznacza jakąś znajomość, 2 solidną wiedzę, 3 wszelkie tajniki)

- **Kapłan**: Zilos 2, Vivera 2, Saera 2 2, Anlena 2, Custus 2, Eheia 2, Deros 2, Oara 2, Khiarus \- 2 \- wybiera swojego boga i o nim wie 3  
- **Paladyn**: Zilos 1, Vivera 1, Saera 1, Anlena 1, Custus 1, Eheia 1, Deros 1, Oara 1, Khiarus \- 2 \- wybiera swojego boga i o nim wie 2  
- **Sługa Boży** (kapłan bez kościoła): Ither \- 2, Kena \- 2, Merdos \- 2, Merena \- 2, Nuvva \- 2, Unises \- 2, Viona \- 2, Wireus \- 2, Yreus \- 2, Khiarus \- 2 \- wybiera swojego boga i o nim wie 3  
- **Czempion** (paladyn bez zakonu): Ither \- 1, Kena \- 1, Merdos \- 1, Merena \- 1, Nuvva \- 1, Unises \- 1, Viona \- 1, Wireus \- 1, Yreus \- 1, Khiarus \- 2 \- wybiera swojego boga i o nim wie 2  
- **Czarownik** (kontrola nad energiami): Eheia \- 1, Yreus \- 1, Ither \- 1, Viona \- 1, Unises \- 1  
- **Czarnoksiężnik** (moc wyciągana siłą z demonów): Khiarus \- 3  
- **Szaman**: Custus \- 1, Eesir \- 1, Yreus \- 1, Wireus \- 2, Merdos \- 2, Thasara \- 1, Inera \- 1  
- **Druid**: Custus \- 2, Ahana \- 1, Merena \- 1, Carisa \- 2, Inera \- 1, Gisstus \- 1  
- **Psion**: Yreus \- 2, Inera \- 2, Viona \- 1  
-   
- **Barbarzyńca**: Kena \- 1, Saera \- 1, Eheia \- 1  
- **Bard**: Kena \- 2, Saera \- 2, Eheia \- 2, Nuvva \- 1, Viona \- 1, Gisstus \- 1, Vivera \- 1  
- **Czarodziej** (wyuczone zaklęcia): Gisstus \- 1, Merena \- 2, Wireus \- 2, Carisa \- 1, Thasara \- 1  
- **Łotrzyk**: Viona \- 2, Nuvva \- 1, Oara \- 2, Kena \- 1  
- **Łowca**: Zilos \- 2, Saera \- 1, Anlena \- 2, Carisa \- 1, Eesir \- 1  
- **Mnich**: Zilos \- 2, Anlena \- 2, Deros \- 2, Yreus \- 2, Ither \- 1, Viona \- 1, Vivera \- 1  
- **Skrytobójca**: Viona \- 3, Deros \- 2, Oara \- 2, Ahana \- 1  
- **Wojownik**: Zilos \- 1, Vivera \- 2, Anlena \- 1, Deros \- 1, Oara \- 1, Nuvva \- 1, Yreus \- 1, Merdos \- 1  
- **Zwiadowca**: Viona \- 3, Vivera \- 2, Saera \- 1, Oara \- 1

Te poniższe może po prostu zmienić w system craftingu?

- Alchemik?  
- Zaklinacz?  
- Kowal?  
- Konstruktor?

Świat \- organizacja

Zilos  
Paladyni: Krasnoludy 70%, Ludzie 30%  
Kapłani: Ludzie 40%, Sarjaki 25%, Gnomy 25%, Mustoki 10%  
Wierni: 

Vivera  
Paladyni: Krasnoludy 10%, Ludzie 65%, Valoci 25%  
Kapłani: Ludzie 40%, Elfy 15%, Haituvi 15%, Mustoki 5%, Valoci 25%  
Wierni: 

Saera  
Paladyni: Ludzie 20%, Valoci 30%, Litory 50%  
Kapłani: Ludzie 15%, Elfy 45%, Haituvi 5%, Valoci 15%, Litory 20%  
Wierni: 

Anlena  
Paladyni: Krasnoludy 35%, Sortaje 15%, Tulevaty 50%  
Kapłani: Sarjaki 40%, Sortaje 10%, Gnomy 20%, Tulevaty 30%  
Wierni: 

Custus  
Paladyni: Ludzie 100%  
Kapłani: Ludzie 30%, Sarjaki 70%  
Wierni: 

Eheia  
Nie posiada zakonu rycerskiego  
Kapłani: Elfy 100%  
Wierni: 

Deros  
Paladyni: Sortaje 45%, Diebeny 20%, Liskoci 30%, Wampiry 5%  
Kapłani: Sarjaki 10%, Sortaje 50%, Gnomy 10%, Liskoci 30%  
Wierni: 

Oara  
Paladyni: Sortaje 40%, Diebeny 60%  
Kapłani: Sortaje 100%  
Wierni: 

Nuvva  
Nie faworyzuje i nie wybiera sobie czempionów  
Słudzy Boży: Gnomy 80%, Tulevaty 20%  
Wierni: 

Merena  
Czempioni: Ludzie 35%, Valoci 45%, Litory 20%  
Słudzy Boży: Ludzie 20%, Haituvi 40%, Mustoki 10%, Valoci 20%, Litory 10%  
Wierni: 

Wireus  
Nie faworyzuje i nie wybiera sobie czempionów  
Słudzy Boży: Haituvi 30%, Mustoki 50%, Tulevaty 20%  
Wierni: 

Kena  
Czempioni: Ilty 10%, Taikani 90%  
Słudzy Boży: Ilty 25%, Taikani 75%  
Wierni: 

Merdos  
Czempioni: Krasnoludy 100%  
Słudzy Boży: Gnomy 25%, Mustoki 5%, Tulevaty 70%  
Wierni: 

Ither  
Czempioni: Valoci 60%, Litory 30%, Taikani 10%  
Słudzy Boży: Elfy 20%, Haituvi 10%, Valoci 50%, Litory 15%, Taikani 5%  
Wierni: 

Viona  
Czempioni: Ilty 100%  
Słudzy Boży: Ilty 100%  
Wierni: 

Unises  
Czempioni: Valoci 35%, Litory 60%, Taikani 5%  
Słudzy Boży: Valoci 20%, Litory 70%, Taikani 10%  
Wierni: 

Yreus  
Czempioni: Liskoci 90%, Wampiry 10%  
Słudzy Boży: Liskoci 70%, Tulevaty 30%  
Wierni: 

**Podział ilościowy:**  
Społeczeństwo: stolica, duże miasta, miasteczka, wsie  
Społeczeństwo: arystokracja 0,5%, rycerstwo 3,94%, duchowni 6,62%, inteligencja 10%, rzemieślnicy 10%, kupcy 5%, robotnicy 20%, bezrobotni, chłopi 40%  
Stolica: arystokracja, rycerstwo, duchowni, inteligencja, rzemieślnicy, kupcy, robotnicy, bezrobotni  
Duże miasta: arystokracja, rycerstwo, duchowni, inteligencja, rzemieślnicy, kupcy, robotnicy, bezrobotni  
Miasteczka: rycerstwo, duchowni, inteligencja, rzemieślnicy, kupcy, robotnicy, bezrobotni  
Wsie: duchowni, inteligencja, rzemieślnicy, kupcy, chłopi

**Podział rasowy:**  
Arystokracja:   
Rycerstwo: (na podstawie paladynów)  
Duchowieństwo: (na podstawie kapłanów)  
Inteligencja:   
Rzemieślnicy:   
Kupcy: Gnomy 40%  
Robotnicy:   
Bezrobotni (złodzieje itp):   
Chłopi: 

**Rycerstwo (zakony) procentowo**:   
Zakon Władzy: 23,62%  
Zakon Porządku: 23,17%  
Zakon Niebios: 9,08%  
Zakon Sprawiedliwości: 9,03%  
Zakon Zdecydowanych: 8,33%  
Zakon Mścicieli: 5,26%  
Zakon Równowagi: 1,13%  
Czempioni Ithera: 4,37%  
Czempioni Unisesa: 3,89%  
Czempioni Mereny: 3,69%  
Czempioni Yreusa: 3,25%  
Czempioni Merdosa: 2,44%  
Czempioni Keny: 1,09%  
Czempioni Viony: 0,82%  
Czempioni Wireusa: 0,81%

**Duchowieństwo procentowo**:  
Kościół Mścicielki Pokrzywdzonych: 18,59%  
Kościół Bezdusznego Włodarza: 12,01%  
Kościół Władczyni Niebios: 11,05%  
Kościół Strażniczki Porządku: 10,07%  
Kościół Sprawiedliwego Sędziego: 3,30%  
Kościół Pana Równowagi: 2,64%  
Kościół Kapryśnej Pani: 2,40%  
Kościół Królowej Zdecydowanych: 1,27%  
Służebnicy Wspólnego Kierunku: 6,50%  
Służebnicy Księcia Świtów: 6,24%  
Służebnicy Złotej Panny: 6,18%  
Służebnicy Strażnika Tajemnic: 5,90%  
Służebnicy Ostoi Udręczonych: 5,76%  
Służebnicy Nieustępliwej Kreatorki: 3,66%  
Służebnicy Oczyszczającego Przypływu: 2,85%  
Służebnicy Pasjonatki Tu i Teraz: 0,87%  
Służebnicy Skrytej Opiekunki: 0,70%

**Odsetek społeczeństwa wierzący w dane bóstwa**: 

Służby porządkowe to paladyni Zilosa, Anleny i Derosa

Geografia i polityka:

Źródła magii i stosunek do niej:
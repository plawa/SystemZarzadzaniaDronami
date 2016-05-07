INSERT INTO `dronymain`.`stacja`
(`ID`,
`Nazwa_stacji`,
`Latitude`,
`Longitude`,
`Attitude`,
`Wolne_miejsca`)
VALUES
(1,
'TestowaS',
1000,
1000,
1000,
2);


INSERT INTO `dronymain`.`stanowiska_ladujace`
(`ID`,
`ID_stacji`,
`czy_zajete`)
VALUES
(1,
1,
0);


INSERT INTO `dronymain`.`drony`
(`ID`,
`ID_stacji`,
`czy_aktywny`,
`stan_streamingu_video`,
`czy_adokowany`,
`Przebieg`)
VALUES
(123,
1,
0,
0,
1,
1234);


INSERT INTO `dronymain`.`parametry_dronow`
(`ID`,
`Model`,
`Ostatni_przeglad`)
VALUES
(123,
'chinski dron z biedronki',
timestamp(now()));

INSERT INTO `dronymain`.`punkty_kontrolne`
(`ID`,
`ID_drona`,
`czas_wyznaczenia`,
`Latitude`,
`Longitude`,
`Attitude`,
`V_max`,
`czy_osiagnieto`)
VALUES
(1,
123,
timestamp(now()),
100,
200,
300,
45,
0);


INSERT INTO `dronymain`.`poziomy_baterii`
(`ID`,
`ID_drona`,
`poziom_baterii`,
`timestamp`)
VALUES
(1,
123,
95,
timestamp(now()));


INSERT INTO `dronymain`.`koordynaty`
(`ID`,
`ID_drona`,
`Timestamp`,
`fix_type`,
`Latitude`,
`longitude`,
`Attitude`,
`Velocity`,
`eph`,
`epv`,
`cog`,
`satellites_visible`)
VALUES
(1,
123,
timestamp(now()),
1,
10,
20,
30,
40,
0.5,
0.6,
0.7,
2);


INSERT INTO `dronymain`.`logi`
(`ID`,
`Timestamp`,
`poziom_wiadomosci`,
`Wiadomosc`)
VALUES
(1,
timestamp(now()),
1,
'Zainicjowano baze danych');


--where clause kan toegevoegd worden om filteren mogelijk te maken
--SELECT VRAAGID, VRAAGVERSIE, THEMANAAM, NAKIJKMODELVERSIE, VRAAGSTELLING, VRAAGPLUGIN, VRAAGOEFENVRAAG, VRAAGGEMAAKTDOOR, VRAAGVERSIEDATUM, VRAAGVERSIEOMSCHRIJVING
SELECT VRAAGID, VRAAGVERSIE, VRAAGNAAM, VRAAGTYPE, VRAAGTHEMA, VRAAGCORRECTEANTWOORDEN, VRAAGNAKIJKINSTRUCTIES, NAKIJKMODELVERSIE, VRAAGSTELLING, VRAAGVERSIEDATUM, VRAAGVERSIEOMSCHRIJVING
from VRAAG
WHERE VRAAGID = ? OR VRAAGVERSIE = ? OR VRAAGTHEMA = ?
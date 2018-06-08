
--where clause kan toegevoegd worden om filteren mogelijk te maken
SELECT TENTAMENCODE, TENTAMENVERSIE, VRAAGID, VRAAGVERSIE, AANTALPUNTEN
FROM VRAAG_VAN_TENTAMEN
WHERE ? = ?
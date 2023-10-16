/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Localization;

/**
 *
 * @author Lake
 */
public class Serbian implements Language {

    @Override
    public String english() {
        return "Engleski";
    }

    @Override
    public String serbian() {
        return "Srpski";
    }

    @Override
    public String copy() {
        return "Kopiraj na clipboard";
    }

    @Override
    public String forLoop() {
        return "For petlja";
    }

    @Override
    public String whileLoop() {
        return "While petlja";
    }

    @Override
    public String repeatLoop() {
        return "Repeat petlja";
    }

    @Override
    public String expression() {
        return "Izraz";
    }

    @Override
    public String remove() {
        return "Brisanje";
    }

    @Override
    public String generate() {
        return "Generiši";
    }

    @Override
    public String exportGeneratedCode() {
        return "Sacuvaj generisani kod";
    }

    @Override
    public String direction() {
        return "\tSmer: ";
    }

    @Override
    public String variable() {
        return "\tPromenljiva: ";
    }

    @Override
    public String iterator() {
        return "\tIterator: ";
    }

    @Override
    public String coreElements() {
        return "Osnovni elementi: ";
    }

    @Override
    public String lower() {
        return "\tDonja granica: ";
    }

    @Override
    public String upper() {
        return "\tGornja granica: ";
    }

    @Override
    public String program() {
        return "Program";
    }

    @Override
    public String complexityFunction() {
        return "\tFunkcija: ";
    }

    @Override
    public String programSegmentComplexity() {
        return "Kompleksnost: ";
    }

    @Override
    public String sideExpressionSingle() {
        return "Sporedni izraz: ";
    }

    @Override
    public String sideExpressionMultiple() {
        return "Sporedni izrazi: ";
    }

    @Override
    public String variableError() {
        return "\nNaziv promenljive u izrazu petlje nije pravilno popunjen.";
    }

    @Override
    public String expressionError(String varTypeText) {
        return "\nIzraz koji se dodeljuje promenljivoj \""
                + varTypeText + "\" nije pravilno popunjen.";
    }

    @Override
    public String directionError() {
        return "\nSmer petlje nije popunjen. Izaberite \"To\" ili \"Downto\".";
    }

    @Override
    public String indexingError() {
        return "\nGreška prilikom kofiguracije indeksiranog pristupa.";
    }

    @Override
    public String generationError() {
        return "Greška prilikom generisanja XML koda!";
    }

    @Override
    public String noContentError() {
        return "Unesite sadržaj na osnovu koga će da se generišu izlazni podaci.";
    }

    @Override
    public String programLanguage() {
        return "Programski jezik";
    }

    @Override
    public String ok() {
        return "Prihvati";
    }

    @Override
    public String cancel() {
        return "Odustani";
    }

    @Override
    public String notAlphanumericWithUnderscoreError() {
        return "Nije alfanumerički sa underscore karakterima";
    }

    @Override
    public String notDecimalError() {
        return "Nije decimalni broj";
    }

    @Override
    public String leftSimpleExpr() {
        return "Levi izraz:";
    }

    @Override
    public String rightSimpleExpr() {
        return "\tDesni izraz:";
    }

    @Override
    public String relop() {
        return "Relacioni operator";
    }

    @Override
    public String Then() {
        return "then";
    }

    @Override
    public String Else() {
        return "else";

    }

    @Override
    public String If() {
        return "if";
    }

    @Override
    public String inIfBranch() {
        return "\tIf grana";
    }

    @Override
    public String inElseBranch() {
        return "\tElse grana";
    }

    @Override
    public String loadTemplate() {
        return "Ucitaj sablon";
    }

    @Override
    public String loadRule() {
        return "Ucitaj pravilo";
    }

    @Override
    public String saveTemplate() {
        return "Sacuvaj sablon";
    }

    @Override
    public String saveRule() {
        return "Sacuvaj pravilo";
    }

    @Override
    public String templateName() {
        return "Ime sablona";
    }

    @Override
    public String templateCreation() {
        return "Kreiranje sablona";
    }

    @Override
    public String ruleCreation() {
        return "Kreiranje pravila";
    }

    @Override
    public String rule() {
        return "Pravilo";
    }

    @Override
    public String template() {
        return "Sablon";
    }

    @Override
    public String ruleName() {
        return "Ime pravila";
    }

    @Override
    public String currentVariables() {
        return "Trenutne promenljive";
    }

    @Override
    public String newVariables() {
        return "Nove promenljive";
    }

    @Override
    public String operation() {
        return "Operacija";
    }

    @Override
    public String sequence() {
        return "Sekvenca";
    }

    @Override
    public String nesting() {
        return "Ugnezdavanje";
    }

    @Override
    public String selection() {
        return "Selekcija";
    }

    @Override
    public String template1OutPorts() {
        return "Izlazni portovi prvog sablona";
    }

    @Override
    public String template2InPorts() {
        return "Ulazni portovi drugog sablona";
    }

    @Override
    public String deleteTemplate() {
        return "Obrisi sablon";
    }

    @Override
    public String deleteRule() {
        return "Obrisi pravilo";
    }

    @Override
    public String rowCount() {
        return "Broj redova";
    }

    @Override
    public String variables() {
        return "Promenljive";
    }

    @Override
    public String error() {
        return "Greška";
    }

    @Override
    public String overwrite() {
        return "Prepisi";
    }

    @Override
    public String noIteratorError() {
        return "Iterator u for petlji mora biti definisan, prilikom konverzije u while petlju";
    }

    @Override
    public String noLowerBoundError() {
        return "For petlja mora imati donju granicu, prilikom konverzije u while petlju";
    }

    @Override
    public String noUpperBoundError() {
        return "For petlja mora imati gornju granicu, prilikom konverzije u while petlju";
    }
}

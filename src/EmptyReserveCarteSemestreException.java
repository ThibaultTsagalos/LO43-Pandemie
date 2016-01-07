package model;

/**
 * Created by Sebastien on 23/12/2015. Used in : Class model.ReserveCarteSemestre , method: piocherCarte AND in
 * Class model.Jeu, method: piocheSemestre
 */
public class EmptyReserveCarteSemestreException extends Exception {

    public EmptyReserveCarteSemestreException(String exceptionMessage){
        super(exceptionMessage);
    }

}

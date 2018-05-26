/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.util;

/**
 *
 * @author KillerWorkstation
 */
public class EnumUtil {
    public enum Cargo {
        GERENTE(0),
        CAIXA(1);

        private final int _value;
        Cargo(int value){_value = value;}
        public int value(){return _value;}
    }   
    
    public enum Pagamento {
        DINHEIRO(0),
        CARTAO(1);

        private final int _value;
        Pagamento(int value){_value = value;}
        public int value(){return _value;}
    } 
    
    public enum Unidade {
        QUILO(0),
        UNIDADE(1);

        private final int _value;
        Unidade(int value){_value = value;}
        public int value(){return _value;}

        @Override
        public String toString() {
            switch (this._value) {
                case 0: 
                    return "Kg";
                case 1:
                    return "Un";
                default:
                    return "";
            }
        }
        
    } 
}

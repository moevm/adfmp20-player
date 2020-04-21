package bus

object Bus {

    private val busArray: ArrayList<ElemBus> = ArrayList();

    public fun emit(str: String){
        for(elem in busArray) {
            if (elem.nameEvent == str) {
                elem.funEvent();
            }
        }
    }
    public fun on(str: String, fn: () -> Any) {
        busArray.add(ElemBus(str, fn));
    }

}



class ElemBus(val nameEvent: String, val funEvent: () -> Any) {
    public fun fn(){
        funEvent();
    }
}

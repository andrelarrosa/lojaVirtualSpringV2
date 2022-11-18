import Axios from "axios";

export class ProdutoService {
    url = process.env.REACT_APP_URI_API+"/produtoImagens/";

    categorias(){
        return Axios.get(this.url);
    }

    inserir(objeto){
        return Axios.post(this.url, objeto);
    }

    alterar(objeto){
        return Axios.put(this.url, objeto);
    }

    excluir(id){
        return Axios.delete(this.url+id);
    }
}

import Axios from "axios";

export class ProdutoImagensService {
    url = process.env.REACT_APP_URI_API+"/produtoImagens/";

    produtoImagens(){
        return Axios.get(this.url);
    }

    uploadImagens(objeto){
        const formData = new FormData();
        formData.append('idProduto', objeto.idProduto);
        formData.append('file', objeto.file);

        const config = {
            headers: {
                'content-type':'multipart/form-data'
            }
        };

        return Axios.post(this.url, formData, config);
    }

    alterar(objeto){
        return Axios.put(this.url, objeto);
    }

    excluir(id){
        return Axios.delete(this.url+id);
    }
}

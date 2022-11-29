import Axios from "axios";

export class LoginService {

    url = process.env.REACT_APP_URI_API+"/pessoa-gerenciamento/";
    CHAVE_TOKEN = "";

    logins(){
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

    login(email, senha, mensagemErro){
        Axios.post( this.url+'login', {'email': email, 'senha': senha}).then(res=>{
            localStorage.setItem(this.CHAVE_TOKEN, res.data.token);
            window.location.href = "/";
        }).catch(error=>{
            mensagemErro(error.response.data.message)
        });
    }

    autenticado(){
        return localStorage.getItem(this.CHAVE_TOKEN)!=null;
    }

    sair(){
        localStorage.removeItem(this.CHAVE_TOKEN);
    }
}

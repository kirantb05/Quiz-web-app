import "../cssFiles/home.css"
import { useNavigate } from "react-router-dom";


function Home(){

    const navigate=useNavigate();
    return(
        <div className="container">

            <h4 className="txt">Welcome To </h4>
            <h1 className="txt">QUIZ</h1>
            <h5 className="txt">Platfrom</h5>

            <button className="btn" type="button" onClick={()=>navigate("/next")}> Next </button>

        </div>
    );
}

export default Home;
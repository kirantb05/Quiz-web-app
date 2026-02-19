import "../cssFiles/result.css"
import { useNavigate } from "react-router-dom";
function Result({score,Reset}){

    const navigate = useNavigate();

    function clicker(){
        Reset(0);
        navigate("/");
    }

    return(
        <div className="container2">

            <div className="score">
                <h1>Your Score is </h1>
                <br/>
                <h2>{score}</h2>
            </div>
            
            <button  id="bt" type="button" onClick={clicker}> Back To Home </button>
        </div>
    );
}

export default Result;
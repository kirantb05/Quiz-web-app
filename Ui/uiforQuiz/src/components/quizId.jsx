import "../cssFiles/home.css"
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
function QuizId(props){
    const navigate=useNavigate();
    const myRef= useRef(null);

     const data=props.indata;
    const setData=props.changeData;

    function changeData(){
        setData(()=>myRef.current.value);
    }

    function out(){
         console.log(data);
         if(data !='' && data != 0){
         navigate("/next/questions");
         }
    }

    return(
        <div className="container">

            <h4>Please Mention </h4>
            <h1>QUIZ ID</h1>
            <h5>Provided by the Admin</h5>

            <input id="inputTag" ref={myRef} type="number" placeholder="Enter the Quiz ID" onChange={changeData} autoFocus></input>

            <button className="btn" type="button" onClick={out} > Get Questions </button>

        </div>
    );
}

export default QuizId;
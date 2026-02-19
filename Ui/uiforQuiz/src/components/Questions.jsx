import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "../cssFiles/questions.css";

function Question({ indata ,setScore}) {
  const [question, setNextQuestion] = useState({
    id: "",
    question: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
  });

  const storeArrayOfObjects = useRef([{}]);
  const storeSizeOfArray = useRef(0);
  const indx = useRef(0);

  const choices = useRef([]);

  const navigate=useNavigate();

  async function getQuestionById() {

    let quesRawObject = await fetch(`http://localhost:8080/quiz/get/${indata}`);

    let realQuestionObject = await quesRawObject.json();

    return realQuestionObject;
  }

  let arr = [];

  function process() {
    const res = getQuestionById();

    if (res != null) {
      res.then((r) => {
        arr = r.map((e) => e);
      });

      res.catch((e) => {
        console.log(e);
      });
    }
  }

  function updateFields(e) {
    if (storeSizeOfArray.current != 0) {

      console.log(storeSizeOfArray.current);

      if (indx.current < storeSizeOfArray.current) {
        setNextQuestion(storeArrayOfObjects.current[indx.current++]);
      }
    }
  }

  

  useEffect(() => {
    process();
   // helper();
  },[]);

  useEffect(()=>{

    setTimeout(()=>{
       if (storeSizeOfArray.current === 0) {
      storeArrayOfObjects.current = arr;
      storeSizeOfArray.current = arr.length;
      setNextQuestion(arr[indx.current++]);
        }
      },1000);
      
    },[]); 
  


  function nextQuestion(e) {

    if(indx.current < storeSizeOfArray.current){
    updateFields(e);
    return;
    }

    if(indx.current === storeSizeOfArray.current){
          e.target.innerText="Submit";
          e.target.style.color="white";
          e.target.style.backgroundColor="red";
          indx.current++;
          return;
        }

    atfinal();
  }

  function clicker(e){

     const child=e.currentTarget;
     const data=child.querySelector("h3");
     console.log(data.innerText);
     if(choices.current.some(obj=>obj.id === question.id)){
       console.log("i have found a exixsting one");
        const  obj=choices.current.find(obj=>obj.id===question.id);
        obj.response=data.innerText;
        
     }else{
      choices.current.push({id:question.id,response:data.innerText});
     }
     
     console.log(choices.current);
     
  }

  async function submitData(){

    let post= await fetch(`http://localhost:8080/quiz/submit/${indata}`,{
      method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(choices.current)
    });

    let rawdata=await post.json();

    return rawdata;
  }

  function atfinal(){

      const submit=submitData();

      if(submit!=null){
          submit.then(res=>{
            console.log(res);
            setScore(()=>res);
            navigate("/score");

          });

          submit.catch(e =>{
            console.log(e);
          }
          );
      }
  }

  

  return(
    <>
      <div className="container1">
        <div className="question">
          <h1 id="text">{question.question}</h1>
        </div>

        <div id="choice" key={question.id}>
          <div className="choice" onClick={(e)=>clicker(e)}>
            <h3>{question.option1}</h3>
          </div>

          <div className="choice" onClick={(e)=>clicker(e)}>
            <h3>{question.option2}</h3>
          </div>

          <div className="choice" onClick={(e)=>clicker(e)}>
            <h3>{question.option3}</h3>
          </div>

          <div className="choice" onClick={(e)=>clicker(e)}>
            <h3>{question.option4}</h3>
          </div>

          <button id="btn" type="button" onClick={(e)=>nextQuestion(e)}>
            next
          </button>
        </div>
      </div>
    </>
  );

}
export default Question;

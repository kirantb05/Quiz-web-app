import { Route, Routes, BrowserRouter } from "react-router-dom";

import Home from "./components/home.jsx";
import QuizId from "./components/quizId.jsx";
import { useState } from "react";
import Question from "./components/Questions.jsx";
import Result from "./components/result.jsx";

function App() {
  const [inData, setInputData] = useState(0);

  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Home />}></Route>

        <Route
          path="/next"
          element={<QuizId indata={inData} changeData={setInputData} />}
        ></Route>

        <Route
          path="/next/questions"
          element={<Question indata={inData} setScore={setInputData}/>}
        ></Route>

        <Route path="/score" element={<Result score={inData} Reset={setInputData}/>}></Route>

      </Routes>
    </BrowserRouter>
  );
}

export default App;

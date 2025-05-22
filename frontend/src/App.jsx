import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import PlannerPage from "./pages/PlannerPage";

function App() {
  const isLoggedIn = !!localStorage.getItem("accessToken");

  return (
    <Router>
      <Routes>
        <Route path="/" element={isLoggedIn ? <PlannerPage /> : <Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  );
}

export default App;


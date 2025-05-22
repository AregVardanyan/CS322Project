import RegisterForm from "../components/RegisterForm";

const RegisterPage = () => {
  const handleRegister = () => {
    alert("Registered successfully. Please login.");
    window.location.href = "/login";
  };

  return (
    <div className="p-4 max-w-md mx-auto mt-10">
      <h2 className="text-2xl font-bold mb-4">Register</h2>
      <RegisterForm onRegister={handleRegister} />
    </div>
  );
};

export default RegisterPage;


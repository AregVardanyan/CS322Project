import LoginForm from "../components/LoginForm";

const LoginPage = () => {
  const handleLogin = () => {
    window.location.href = "/";
  };

  return (
    <div className="p-4 max-w-md mx-auto mt-10">
      <h2 className="text-2xl font-bold mb-4">Login</h2>
      <LoginForm onLogin={handleLogin} />
    </div>
  );
};

export default LoginPage;


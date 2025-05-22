import { useState } from "react";
import axios from "../api/axios";

export default function PlannerForm() {
  const [form, setForm] = useState({
    prefTopics: [],
    year: new Date().getFullYear(),
    semester: "FALL",
    prefLoad: "TTH",
  });

  const [result, setResult] = useState("");

  const handleChange = e => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleTopicsChange = e => {
    setForm({ ...form, prefTopics: e.target.value.split(",").map(t => t.trim()) });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    const res = await axios.post("/api/plan", form);
    setResult(res.data);
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Preferred topics (comma separated)" onChange={handleTopicsChange} />
        <input type="number" name="year" value={form.year} onChange={handleChange} />
        <select name="semester" value={form.semester} onChange={handleChange}>
          <option value="FALL">Fall</option>
          <option value="SPRING">Spring</option>
	  <option value="SUMMER">Spring</option>
	  
        </select>
        <select name="prefLoad" value={form.prefLoad} onChange={handleChange}>
          <option value="MWF">MWF</option>
          <option value="TTH">TTH</option>
        </select>
        <button type="submit">Generate Plan</button>
      </form>
      {result && <pre>{result}</pre>}
    </>
  );
}


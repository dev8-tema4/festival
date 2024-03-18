const writeFrm = document.querySelector("#writeFrm");


const submitHandler = (e)=>{
    e.preventDefault();
    const subject = e.target.subject.value;
    const writer = e.target.subject.value;
    const content = e.target.content.value;
   
};

writeFrm.addEventListener("submit", submitHandler);

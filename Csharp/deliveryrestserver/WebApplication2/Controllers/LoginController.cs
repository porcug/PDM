using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LoginController : ControllerBase
    {
        [HttpPost]
        public ActionResult<Token> Login([FromBody] LoginInfo info)
        {
            String id = RandomId();
            return new Token(id);
        }

       
        private string RandomId()
        {
            String symbols = "1qaz2wsx3edc4rfv5tgb6yhn7ujm8ik9ol0pABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random rand = new Random((int)DateTime.Now.Ticks);
            int size = rand.Next(10, 21);
            StringBuilder sb = new StringBuilder();
            for(;size>0;size--)
            {
                sb.Append(symbols[rand.Next(0, symbols.Length)]);
            }
            return sb.ToString();
        }
    }
}

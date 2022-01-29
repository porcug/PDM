using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ImageController : ControllerBase
    {
        [HttpPost]
        public async Task<ImageResponse> SaveImage()
        {
            String id = "image_" + RandomId();
            using (Stream reader = Request.Body)
            {
                System.IO.Directory.CreateDirectory("./image/");
                FileStream writer = new FileStream("./image/" + id + ".jpg",FileMode.CreateNew);
                byte[] buffer = new byte[1024 * 1024];
                int read = 0;
                do
                {
                    read = await reader.ReadAsync(buffer, 0, 1024 * 1024);
                   
                    await writer.WriteAsync(buffer, 0, read);
                }
                while (read > 0);
                writer.Flush();
                writer.Close();
            }
            return new ImageResponse(id);
        }

        [HttpGet("{id}")]
        public IActionResult Values(string id)
        {
            try 
            {
                string path = "./image/" + id + ".jpg";
                FileStream stream = new FileStream(path,FileMode.Open);
                return File(stream, "image/jpeg");
            }
            catch { 
                return Ok();
            }
        }
        public async Task<HttpResponseMessage> Download(string id)
        {
            var response = new HttpResponseMessage(System.Net.HttpStatusCode.OK)
            {
                Content = new PushStreamContent(async (stream, context, transportContext) =>
                {
                    try
                    {
                        using (var fileStream = System.IO.File.OpenRead("./image/" + id + ".jpg"))
                        {
                            await fileStream.CopyToAsync(stream);
                        }
                    }
                    finally
                    {
                        stream.Close();
                    }
                }, "application/octet-stream"),
            };
            response.Headers.TransferEncodingChunked = true;
            response.Content.Headers.ContentDisposition = new System.Net.Http.Headers.ContentDispositionHeaderValue("attachment")
            {
                FileName = "image.jpg"
            };
            return response;
        }
        private string RandomId()
        {
            String symbols = "1qaz2wsx3edc4rfv5tgb6yhn7ujm8ik9ol0pABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random rand = new Random();
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

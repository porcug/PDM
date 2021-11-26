using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplication2.Models
{
    public class ImageResponse
    { 
        public ImageResponse(string s)
        {
            imagename = s;
        }

        public String imagename { get; set; }
    }
}

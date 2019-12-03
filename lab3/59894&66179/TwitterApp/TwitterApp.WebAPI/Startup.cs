using AutoMapper;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using TwitterApp.BuisnessLogic.Infrastructure;
using TwitterApp.BuisnessLogic.Infrastructure.DateConverter;
using TwitterApp.BuisnessLogic.UserService;
using TwitterApp.BuisnessLogic.WordsMapService;
using TwitterApp.BuisnessLogic.TweetIdsService;
using TwitterApp.TwitterDataAccessLayer.Interfaces;
using TwitterApp.TwitterDataAccessLayer.TwitterApiServices;

namespace TwitterApp.WebAPI
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            ConfigureSpecificServices(services);
            ConfigureAutoMapper(services);
            services.AddCors();
            services.AddMvc().SetCompatibilityVersion(CompatibilityVersion.Version_3_0);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseCors(options =>
            {
                options.AllowAnyOrigin();
                options.AllowAnyMethod();
                options.AllowAnyHeader();
            });

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }

        private void ConfigureSpecificServices(IServiceCollection services)
        {
            services.AddSingleton<IOAuthService, OAuthService>();
            services.AddTransient<IApiService, ApiService>();
            services.AddTransient<IUserService, UserService>();
            services.AddSingleton<IDateConverter, DateConverter>();
            services.AddTransient<IWordsMapService, WordsMapService>();
            services.AddTransient<ITweetIdsService, TweetIdsService>();
        }

        private void ConfigureAutoMapper(IServiceCollection services)
        {
            var mappingConfig = new MapperConfiguration(mc =>
           {
               mc.AddProfile(new MappingProfile());
           });
            IMapper mapper = mappingConfig.CreateMapper();
            services.AddSingleton(mapper);
        }
    }
}
